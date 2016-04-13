package romana.vlad.mengyang.learningtolisten;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class GraphResultActivity extends AppCompatActivity {

    private Grade grade;

    private Setting setting;

    private void setChart() {
        LineChart chart = (LineChart) findViewById(R.id.linechart_grade);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);

        YAxis yAxis = chart.getAxisLeft();
        chart.getAxisRight().setEnabled(false);
        yAxis.setAxisMinValue(-40f);
        yAxis.setAxisMaxValue(70f);
        yAxis.setDrawLabels(true);
        yAxis.setDrawAxisLine(true);
        yAxis.setDrawGridLines(false);
        chart.getAxisRight().setEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();
        grade.display();
        ArrayList<Integer> gradeInDifficulty = grade.getDifficultyArrayList();
        for (int i = 0; i < gradeInDifficulty.size(); i++) {
            Entry entry = new Entry(60 - gradeInDifficulty.get(i), i);
            yValues.add(entry);
        }

        ILineDataSet lineDataSet = new LineDataSet(yValues, "SNR-Trial");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 0; i < gradeInDifficulty.size(); i++) {
            xValues.add(String.format("%d", i + 1));
        }

        LineData lineData = new LineData(xValues, dataSets);
        chart.setData(lineData);
        chart.invalidate();

    }

//    private class MyTask extends AsyncTask<String, String, String> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Log.e("LAG", "Background Thread");
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            connectServer();
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            Log.e("LAG", "Background Thread ended");
//            super.onPostExecute(s);
//        }
//    }
//
//    protected boolean isOnline() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    protected void connectServer() {
//        if (isOnline()) {
//            Log.e("LAG", "Online");
//            setData();
//        } else {
//            Log.e("LAG", "Offline");
//        }
//    }
//
//    private void setData() {
//        URL url;
//        HttpURLConnection httpURLConnection;
//        try {
//            url = new URL("http://52.35.91.41:8000/scorepost");
//            httpURLConnection = (HttpURLConnection) url.openConnection();
//            //Log.e("LAG", "OK");
//            httpURLConnection.setDoOutput(true);
//            Log.e("LAG", "OK");
//            httpURLConnection.setRequestMethod("POST");
//            //httpURLConnection.setChunkedStreamingMode(0);
//            Log.e("LAG", "OK");
//            String data = URLEncoder.encode("user_name", "UTF-8")
//                    + "=" + URLEncoder.encode(setting.getUserName(), "UTF-8");
//            Log.e("LAG", "OK");
//            OutputStreamWriter writer= new OutputStreamWriter(httpURLConnection.getOutputStream());
//            Log.e("LAG", "OK");
//            writer.write(data);
//            writer.flush();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            // Read Server Response
//            while((line = reader.readLine()) != null)
//            {
//                // Append server response in string
//                sb.append(line + "\n");
//            }
//            Log.e("LAG", sb.toString());
//            httpURLConnection.disconnect();
//        } catch (Exception e) {
//            Log.e("LAG", e.toString());
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_result);
        grade = (Grade) getIntent().getSerializableExtra("Result");
        setting = (Setting) getIntent().getSerializableExtra("Setting");
        setChart();
        EditText emailAddress = (EditText) findViewById(R.id.editEmail);
        emailAddress.setText(setting.getEmailAddress());
    }

    public void onClickCancel(View view) {
        finish();
    }

    public void onClickOK(View view) {
        EditText emailAddress = (EditText) findViewById(R.id.editEmail);
        Editable email = emailAddress.getText();
        String[] TO = new String[1];
        TO[0] = email.toString();
        String CC = "";
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Results--Learning to Listen game");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}

package romana.vlad.mengyang.learningtolisten;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
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

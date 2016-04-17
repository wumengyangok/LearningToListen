package romana.vlad.mengyang.learningtolisten;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 0;
    private Setting setting;
    private String testString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setting = new Setting();
        setting.addAnimalToList(Setting.Animal.dog);
        setting.addSpeakerToList(Setting.Speaker.Man);
    }

    public void onClickGetInfo(View view) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    public void onClickPlay(View view) {
        Intent intent;
        switch (setting.getMode()){
            case EASY:
                intent = new Intent(this, EasyModeActivity.class);
                break;
            case NORMAL:
                intent = new Intent(this, MediumModeActivity.class);
                break;
            case CRAZY:
                intent = new Intent(this, HardModeActivity.class);
                break;
            default:
                intent = new Intent(this, EasyModeActivity.class);
                break;
        }
        intent.putExtra("Setting", setting);
        startActivity(intent);
    }

    public void onClickSetting(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("Setting", setting);
        startActivityForResult(intent, REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String key = "Setting";
                setting = (Setting) data.getExtras().getSerializable(key);
            }
        }
        Log.e("LAG", setting.getMode().toString());
        Log.e("LAG", setting.getVoiceFrom().toString());
        Log.e("LAG", setting.getEmailAddress().toString());
        Log.e("LAG", Boolean.toString(setting.getAgreement()));
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Exit the game?")
                .setCancelable(true)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}

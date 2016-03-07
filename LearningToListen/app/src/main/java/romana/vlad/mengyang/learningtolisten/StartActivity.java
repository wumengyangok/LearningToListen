package romana.vlad.mengyang.learningtolisten;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.Normalizer;

public class StartActivity extends AppCompatActivity {

    private Setting setting;

    private String testString;

    private final int REQUEST_CODE = 0;

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
    }

}

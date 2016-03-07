package romana.vlad.mengyang.learningtolisten;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity {

    private Setting newSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Spinner modeSpinner = (Spinner) findViewById(R.id.difficulties_spinner);
        ArrayAdapter<CharSequence> modeAdapter = ArrayAdapter.createFromResource(this, R.array.difficulties, android.R.layout.simple_spinner_item);
        modeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setAdapter(modeAdapter);

        newSetting = new Setting();
    }

    public void onClickDog(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.checkMarkDog);
        if (newSetting.ifInAnimalList(Setting.Animal.dog)) {
            imageView.setVisibility(View.INVISIBLE);
            newSetting.deleteAnimalFromList(Setting.Animal.dog);
        } else {
            imageView.setVisibility(View.VISIBLE);
            newSetting.addAnimalToList(Setting.Animal.dog);
        }
    }

    public void onClickCat(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.checkMarkCat);
        if (newSetting.ifInAnimalList(Setting.Animal.cat)) {
            imageView.setVisibility(View.INVISIBLE);
            newSetting.deleteAnimalFromList(Setting.Animal.cat);
        } else {
            imageView.setVisibility(View.VISIBLE);
            newSetting.addAnimalToList(Setting.Animal.cat);
        }
    }

    public void onClickCow(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.checkMarkCow);
        if (newSetting.ifInAnimalList(Setting.Animal.cow)) {
            imageView.setVisibility(View.INVISIBLE);
            newSetting.deleteAnimalFromList(Setting.Animal.cow);
        } else {
            imageView.setVisibility(View.VISIBLE);
            newSetting.addAnimalToList(Setting.Animal.cow);
        }
    }

    public void onClickSheep(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.checkMarkSheep);
        if (newSetting.ifInAnimalList(Setting.Animal.sheep)) {
            imageView.setVisibility(View.INVISIBLE);
            newSetting.deleteAnimalFromList(Setting.Animal.sheep);
        } else {
            imageView.setVisibility(View.VISIBLE);
            newSetting.addAnimalToList(Setting.Animal.sheep);
        }

    }

    public void onClickDuck(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.checkMarkDuck);
        if (newSetting.ifInAnimalList(Setting.Animal.duck)) {
            imageView.setVisibility(View.INVISIBLE);
            newSetting.deleteAnimalFromList(Setting.Animal.duck);
        } else {
            imageView.setVisibility(View.VISIBLE);
            newSetting.addAnimalToList(Setting.Animal.duck);
        }

    }

    public void onClickPig(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.checkMarkPig);
        if (newSetting.ifInAnimalList(Setting.Animal.pig)) {
            imageView.setVisibility(View.INVISIBLE);
            newSetting.deleteAnimalFromList(Setting.Animal.pig);
        } else {
            imageView.setVisibility(View.VISIBLE);
            newSetting.addAnimalToList(Setting.Animal.pig);
        }

    }

    public void onClickMan(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.checkMarkMan);
        if (newSetting.ifInSpeakerList(Setting.Speaker.Man)) {
            imageView.setVisibility(View.INVISIBLE);
            newSetting.deleteSpeakerFromList(Setting.Speaker.Man);
        } else {
            imageView.setVisibility(View.VISIBLE);
            newSetting.addSpeakerToList(Setting.Speaker.Man);
        }

    }

    public void onClickWoman(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.checkMarkWoman);
        if (newSetting.ifInSpeakerList(Setting.Speaker.Woman)) {
            imageView.setVisibility(View.INVISIBLE);
            newSetting.deleteSpeakerFromList(Setting.Speaker.Woman);
        } else {
            imageView.setVisibility(View.VISIBLE);
            newSetting.addSpeakerToList(Setting.Speaker.Woman);
        }
    }

    public void onClickChild(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.checkMarkChild);
        if (newSetting.ifInSpeakerList(Setting.Speaker.Child)) {
            imageView.setVisibility(View.INVISIBLE);
            newSetting.deleteSpeakerFromList(Setting.Speaker.Child);
        } else {
            imageView.setVisibility(View.VISIBLE);
            newSetting.addSpeakerToList(Setting.Speaker.Child);
        }
    }

    public void onClickSave(View view) {
        Spinner modeSpinner = (Spinner) findViewById(R.id.difficulties_spinner);
        newSetting.setMode(Setting.Mode.valueOf((String) modeSpinner.getSelectedItem()));
        RadioGroup orientationRadioGroup = (RadioGroup) findViewById(R.id.orientation_radio_group);
        switch (orientationRadioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_button_both:
                newSetting.setVoiceFrom(Setting.VoiceFrom.BOTH);
                break;
            case R.id.radio_button_left:
                newSetting.setVoiceFrom(Setting.VoiceFrom.LEFT);
                break;
            case R.id.radio_button_right:
                newSetting.setVoiceFrom(Setting.VoiceFrom.RIGHT);
                break;
            default:
                break;
        }
        Intent resultIntent = new Intent();
        resultIntent.putExtra("Setting", newSetting);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}

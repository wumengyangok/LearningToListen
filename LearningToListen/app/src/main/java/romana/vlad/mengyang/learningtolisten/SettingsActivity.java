package romana.vlad.mengyang.learningtolisten;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.lang.reflect.Field;

public class SettingsActivity extends AppCompatActivity {

    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private Setting newSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Spinner modeSpinner = (Spinner) findViewById(R.id.difficulties_spinner);
        ArrayAdapter<CharSequence> modeAdapter = ArrayAdapter.createFromResource(this, R.array.difficulties, android.R.layout.simple_spinner_item);
        modeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setAdapter(modeAdapter);

        newSetting = (Setting) getIntent().getSerializableExtra("Setting");

        modeSpinner.setSelection(modeAdapter.getPosition(newSetting.getMode().name()));
        ImageView imageView;
        for (Setting.Animal animal : newSetting.getAnimalsArrayList()) {
            String checkMarkName = new String("checkMark" + animal);
            imageView = (ImageView) findViewById(getResId(checkMarkName, R.id.class));
            imageView.setVisibility(View.VISIBLE);
        }

        for (Setting.Speaker speaker : newSetting.getSpeakersArrayList()) {
            String checkMarkName = new String("checkMark" + speaker);
            imageView = (ImageView) findViewById(getResId(checkMarkName, R.id.class));
            imageView.setVisibility(View.VISIBLE);
        }

        RadioGroup orientationRadioGroup = (RadioGroup) findViewById(R.id.orientation_radio_group);
        String checkedName = new String("radio_button_" + newSetting.getVoiceFrom());
        orientationRadioGroup.check(getResId(checkedName, R.id.class));
    }

    public void onClickDog(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.checkMarkdog);
        if (newSetting.ifInAnimalList(Setting.Animal.dog)) {
            imageView.setVisibility(View.INVISIBLE);
            newSetting.deleteAnimalFromList(Setting.Animal.dog);
        } else {
            imageView.setVisibility(View.VISIBLE);
            newSetting.addAnimalToList(Setting.Animal.dog);
        }
    }

    public void onClickCat(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.checkMarkcat);
        if (newSetting.ifInAnimalList(Setting.Animal.cat)) {
            imageView.setVisibility(View.INVISIBLE);
            newSetting.deleteAnimalFromList(Setting.Animal.cat);
        } else {
            imageView.setVisibility(View.VISIBLE);
            newSetting.addAnimalToList(Setting.Animal.cat);
        }
    }

    public void onClickCow(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.checkMarkcow);
        if (newSetting.ifInAnimalList(Setting.Animal.cow)) {
            imageView.setVisibility(View.INVISIBLE);
            newSetting.deleteAnimalFromList(Setting.Animal.cow);
        } else {
            imageView.setVisibility(View.VISIBLE);
            newSetting.addAnimalToList(Setting.Animal.cow);
        }
    }

    public void onClickSheep(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.checkMarksheep);
        if (newSetting.ifInAnimalList(Setting.Animal.sheep)) {
            imageView.setVisibility(View.INVISIBLE);
            newSetting.deleteAnimalFromList(Setting.Animal.sheep);
        } else {
            imageView.setVisibility(View.VISIBLE);
            newSetting.addAnimalToList(Setting.Animal.sheep);
        }

    }

    public void onClickDuck(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.checkMarkduck);
        if (newSetting.ifInAnimalList(Setting.Animal.duck)) {
            imageView.setVisibility(View.INVISIBLE);
            newSetting.deleteAnimalFromList(Setting.Animal.duck);
        } else {
            imageView.setVisibility(View.VISIBLE);
            newSetting.addAnimalToList(Setting.Animal.duck);
        }

    }

    public void onClickPig(View view) {
        ImageView imageView = (ImageView) findViewById(R.id.checkMarkpig);
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

    private void save() {
        Spinner modeSpinner = (Spinner) findViewById(R.id.difficulties_spinner);
        newSetting.setMode(Setting.Mode.valueOf((String) modeSpinner.getSelectedItem()));
        RadioGroup orientationRadioGroup = (RadioGroup) findViewById(R.id.orientation_radio_group);
        switch (orientationRadioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_button_BOTH:
                newSetting.setVoiceFrom(Setting.VoiceFrom.BOTH);
                break;
            case R.id.radio_button_LEFT:
                newSetting.setVoiceFrom(Setting.VoiceFrom.LEFT);
                break;
            case R.id.radio_button_RIGHT:
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

    public void onClickSave(View view) {
        save();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Would you like to save the change?")
                .setCancelable(true)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        save();
                    }
                })
                .setNegativeButton("NO, leave settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNeutralButton("Stay to edit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}

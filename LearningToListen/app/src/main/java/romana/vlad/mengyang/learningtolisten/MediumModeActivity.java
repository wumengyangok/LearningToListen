package romana.vlad.mengyang.learningtolisten;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


public class MediumModeActivity extends AppCompatActivity {
    private EasyGame mediumMode;

    // initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_mode);
        mediumMode = new EasyGame(
                this,
                (Setting) getIntent().getSerializableExtra("Setting"),
                (ImageView) findViewById(R.id.animal_medium)
        );
        mediumMode.addNumber(8);
        mediumMode.addNumber(2);
        mediumMode.run();
    }

    // Handle the click
    public void onClickMedium(View view) {
        if (mediumMode.audioFileMask.isFinished() && mediumMode.audioFileTarget.isFinished()) {
            mediumMode.setNullAudioFile();
            String iconName = view.getResources().getResourceName(view.getId());
            mediumMode.decide(iconName);
        }
    }
}

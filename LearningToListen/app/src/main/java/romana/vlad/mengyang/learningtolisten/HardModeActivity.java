package romana.vlad.mengyang.learningtolisten;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class HardModeActivity extends AppCompatActivity {
    private HardGame hardMode;

    // initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_mode);
        hardMode = new HardGame(
                this,
                (Setting) getIntent().getSerializableExtra("Setting"),
                (ImageView) findViewById(R.id.animal_hard)
        );
        hardMode.addNumber(8);
        hardMode.addNumber(2);
        hardMode.addNumber(6);
        hardMode.addNumber(9);
        hardMode.prepare();
        hardMode.run();
    }

    // Handle the click
    public void onClickHard(View view) {
        if (hardMode.audioFileMask.isFinished() && hardMode.audioFileTarget.isFinished()) {
            String iconName = view.getResources().getResourceName(view.getId());
            Log.e("LAG", iconName);
            iconName = new String(hardMode.getButtonName(iconName));
            Log.e("LAG", iconName);
            hardMode.decide(iconName);
        }

    }
}

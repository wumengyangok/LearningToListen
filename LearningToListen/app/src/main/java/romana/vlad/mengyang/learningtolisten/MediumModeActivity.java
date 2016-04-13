package romana.vlad.mengyang.learningtolisten;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


public class MediumModeActivity extends AppCompatActivity {
    private Game mediumMode;

    // initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_mode);
        mediumMode = new Game(
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
        String iconName = view.getResources().getResourceName(view.getId());
        mediumMode.decide(iconName);
    }
}

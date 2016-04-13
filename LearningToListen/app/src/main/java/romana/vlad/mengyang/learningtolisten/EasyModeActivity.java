package romana.vlad.mengyang.learningtolisten;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class EasyModeActivity extends AppCompatActivity {

    private Game easyMode;

    // initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_mode);
        easyMode = new Game(
                this,
                (Setting) getIntent().getSerializableExtra("Setting"),
                (ImageView) findViewById(R.id.animal_easy)
        );
        easyMode.addNumber(8);
        easyMode.run();
    }

    // Handle the click
    public void onClickEasy(View view) {
        String iconName = view.getResources().getResourceName(view.getId());
        easyMode.decide(iconName);
    }
}

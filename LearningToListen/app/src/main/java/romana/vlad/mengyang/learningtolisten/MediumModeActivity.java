package romana.vlad.mengyang.learningtolisten;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;


public class MediumModeActivity extends AppCompatActivity {

    private String fileName;
    private Setting.VoiceFrom playMode;
    private int volume;
    private final int delay = 10;
    private final float difference = 0.3f;

    private void setAudioFile(String fileName, Setting.VoiceFrom playMode, int volume) {
        this.volume = volume;
        this.fileName = fileName;
        this.playMode = playMode;
    }

    // Play audio file with playMode
    public void play() {

        int fileUri = getResId(fileName, R.raw.class);
        MediaPlayer mediaPlayerLeft = MediaPlayer.create(this, fileUri);
        MediaPlayer mediaPlayerRight = MediaPlayer.create(this, fileUri);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, fileUri);
        switch (playMode) {
            case LEFT:
                mediaPlayerLeft.setVolume(volume / 100.0f, 0.0f);
                mediaPlayerRight.setVolume(0.0f, volume / 100.0f - difference);
                mediaPlayerLeft.seekTo(delay);
                mediaPlayerLeft.start();
                mediaPlayerRight.start();
                break;
            case RIGHT:
                mediaPlayerRight.setVolume(0.0f, volume / 100.0f);
                mediaPlayerLeft.setVolume(volume / 100.0f - difference, 0.0f);
                mediaPlayerRight.seekTo(delay);
                mediaPlayerRight.start();
                mediaPlayerLeft.start();
                break;
            case BOTH:
                mediaPlayer.setVolume(volume / 100.0f, volume / 100.0f);
                mediaPlayer.start();
                break;
            default:
                break;
        }
    }

    private Setting setting;

    //Count the trials
    private int countDown;

    private ArrayList<Setting.Animal> animalArrayList;

    private ArrayList<Setting.Speaker> speakerArrayList;

    private ArrayList<Integer> numberArrayList;

    private int difficulty;

    private int numberAnimal;

    private int numberSpeaker;

    private int numberInteger;

    private String correctAnswer;

    private final int numberColor = 6;

    private String[] colorArray = {
            "black", "red", "blue", "pink", "green", "white"
    };

    private String[] animalArray = {
            "pig", "dog", "cat", "duck", "cow", "sheep"
    };

    private Grade grade;

    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // get a random number without repetition
    public int randomAvoid(int range, int avoid) {
        Log.e("LAG", String.valueOf(avoid));
        Log.e("LAG", String.valueOf(range));
        Random random = new Random();
        int number = random.nextInt(range);
        while (number == avoid)
            number = random.nextInt(range);
        Log.e("LAG", String.valueOf(number));
        return number;
    }

    // get a random string without repetition
    public String randomAvoid(String[] range, String avoid) {
        int index = 0;
        for (index = 0; index < range.length; index++) {
            if (range[index].equals(avoid))
                break;
        }
        return range[randomAvoid(range.length, index)];
    }

    // run a trial
    public void run() {
        int animal = randomAvoid(numberAnimal, -1);
        int color = randomAvoid(numberColor, -1);
        int number = randomAvoid(numberInteger, -1);
        StringBuilder fileName = new StringBuilder();
        fileName.append(animalArrayList.get(animal)).append('_').append(colorArray[color]).append('_').append(numberArrayList.get(number));
        Log.e("LAG", fileName.toString());
        setAudioFile(fileName.toString(), setting.getVoiceFrom(), 60);
        play();

        String animalMask = randomAvoid(animalArray, animalArrayList.get(animal).name());
        String colorMask = randomAvoid(colorArray, colorArray[color]);
        int numberMask = randomAvoid(numberInteger, number);
        StringBuilder maskName = new StringBuilder();
        maskName.append(animalMask).append('_').append(colorMask).append('_').append(numberArrayList.get(numberMask));
        Log.e("LAG", maskName.toString());
        setAudioFile(maskName.toString(), Setting.VoiceFrom.BOTH, difficulty);
        play();
        correctAnswer = new String(colorArray[color] + "_" + numberArrayList.get(number));

        ImageView face = (ImageView) findViewById(R.id.animal_medium);
        face.setImageResource(getResId(animalArrayList.get(animal).toString(), R.drawable.class));
    }

    public void pause() {

    }

    public void exit() {
        Intent intent = new Intent(this, GraphResultActivity.class);
        intent.putExtra("Result", grade);
        intent.putExtra("Setting", setting);
        startActivity(intent);
        finish();
    }
    // initialisation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_mode);
        setting = (Setting) getIntent().getSerializableExtra("Setting");
        countDown = setting.getNumberOfTrials();
        animalArrayList = setting.getAnimalsArrayList();
        speakerArrayList = setting.getSpeakersArrayList();
        numberArrayList = new ArrayList<>();
        numberArrayList.add(8);
        numberArrayList.add(2);
        numberAnimal = animalArrayList.size();
        numberSpeaker = speakerArrayList.size();
        numberInteger = numberArrayList.size();
        grade = new Grade(playMode, Setting.Mode.NORMAL);
        run();
    }

    // Handle the click
    public void onClick(View view) {

        String iconName = view.getResources().getResourceName(view.getId());
        if (iconName.contains(correctAnswer)) {
            Toast.makeText(MediumModeActivity.this, "Right", Toast.LENGTH_SHORT).show();
            grade.addGrade(difficulty, true);
            difficulty += 20;
            if (difficulty > 100)
                difficulty = 100;
        } else {
            Toast.makeText(MediumModeActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
            grade.addGrade(difficulty, false);
            if (difficulty > 0) {
                difficulty -= 20;
            }
        }
        countDown--;
        if (countDown == 0) {
            //Toast.makeText(MediumModeActivity.this, "Finished", Toast.LENGTH_SHORT).show();
            exit();
        } else {
            run();
        }
        Log.e("LAG", iconName);
    }

}

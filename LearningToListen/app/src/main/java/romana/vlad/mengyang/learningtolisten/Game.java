package romana.vlad.mengyang.learningtolisten;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

//Controller

public abstract class Game {

    protected final int numberColor = 6;
    protected ImageView imageView;
    protected Context context;
    protected Setting setting;
    protected Grade grade;
    protected ArrayList<Setting.Animal> animalArrayList;
    protected ArrayList<Setting.Speaker> speakerArrayList;
    protected ArrayList<Integer> numberArrayList;
    protected String[] colorArray = {
            "black", "red", "blue", "pink", "green", "white"
    };
    protected String[] animalArray = {
            "pig", "dog", "cat", "duck", "cow", "sheep"
    };
    protected String[] speakerArray = {
            "man", "woman", "child"
    };

    protected int[] SNR = {
            40, 20, 10, 6, 3, 0, -3, -6, -10, -20, -40
    };

    //Count the trials
    protected int countDown;
    protected int difficulty;
    protected int numberAnimal;
    protected int numberSpeaker;
    protected int numberInteger;
    protected String correctAnswer;
    protected AudioFile audioFileTarget;
    protected AudioFile audioFileMask;
    protected int animal;
    protected float targetVolume;
    protected float maskVolume;

    public Game(Context context, Setting setting, ImageView imageView) {
        this.context = context;
        this.setting = setting;
        countDown = setting.getNumberOfTrials();
        animalArrayList = setting.getAnimalsArrayList();
        speakerArrayList = setting.getSpeakersArrayList();
        numberArrayList = new ArrayList<>();
        numberAnimal = animalArrayList.size();
        numberSpeaker = speakerArrayList.size();
        numberInteger = numberArrayList.size();
        grade = new Grade(setting.getVoiceFrom(), setting.getMode());
        this.imageView = imageView;
        difficulty = 0;
    }

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
    public static int randomAvoid(int range, int avoid) {
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
    public static String randomAvoid(String[] range, String avoid) {
        int index = 0;
        for (index = 0; index < range.length; index++) {
            if (range[index].equals(avoid))
                break;
        }
        return range[randomAvoid(range.length, index)];
    }

    public int getCountDown() {
        return countDown;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void addNumber(int number) {
        numberArrayList.add(number);
        numberInteger++;
    }

    protected void calculateFromSNR() {
        float snr = SNR[difficulty];
        float k = (float) Math.pow(10, snr / 20.0);
        targetVolume = (float) (k / Math.sqrt(k * k + 1));
        maskVolume = (float) Math.sqrt(1 - targetVolume * targetVolume);
    }

    protected void playTarget(int animal, int color, int number, int speaker) {
        StringBuilder fileName = new StringBuilder();
        fileName.append(animalArrayList.get(animal)).append('_').append(colorArray[color]).append('_').append(numberArrayList.get(number)).append("_").append(speakerArrayList.get(speaker).name().toLowerCase());
        Log.e("LAG", fileName.toString());
        audioFileTarget = new AudioFile(context, fileName.toString(), setting.getVoiceFrom(), targetVolume);
        audioFileTarget.play();
    }

    protected void playMask(int animal, int color, int number, int speaker) {
        String animalMask = randomAvoid(animalArray, animalArrayList.get(animal).name());
        String colorMask = randomAvoid(colorArray, colorArray[color]);
        String speakerMask = randomAvoid(speakerArray, speakerArrayList.get(speaker).name().toLowerCase());
        StringBuilder maskName = new StringBuilder();
        maskName.append(animalMask).append('_').append(colorMask).append('_').append(numberArrayList.get(number)).append("_").append(speakerMask);
        Log.e("LAG", maskName.toString());
        audioFileMask = new AudioFile(context, maskName.toString(), Setting.VoiceFrom.BOTH, maskVolume);
        audioFileMask.play();
        correctAnswer = new String(colorArray[color] + "_" + numberArrayList.get(number));
        delay();
    }

    // run a trial
    public void run() {

    }

    public void pause() {

    }

    public void delay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.setImageResource(getResId(animalArrayList.get(animal).toString(), R.drawable.class));
            }
        }, 500);
    }

    public void decide(String iconName) {
        if (iconName.contains(correctAnswer)) {
            Toast.makeText(context, "Right", Toast.LENGTH_SHORT).show();
            grade.addGrade(SNR[difficulty], true);
            difficulty += 1;
            if (difficulty > 10)
                difficulty = 10;
            Log.e("LAG", animalArrayList.get(animal).toString() + "_happy");
            imageView.setImageResource(getResId(animalArrayList.get(animal).toString() + "_happy", R.drawable.class));
        } else {
            Toast.makeText(context, "Wrong", Toast.LENGTH_SHORT).show();
            grade.addGrade(SNR[difficulty], false);
            if (difficulty > 0) {
                difficulty -= 1;
            }
            Log.e("LAG", animalArrayList.get(animal).toString() + "_sad");
            imageView.setImageResource(getResId(animalArrayList.get(animal).toString() + "_sad", R.drawable.class));
        }
        countDown--;
        if (countDown == 0) {
            exit();
        } else {
            run();
        }
        Log.e("LAG", iconName);
    }

    public void exit() {
        Intent intent = new Intent(context, GraphResultActivity.class);
        intent.putExtra("Result", grade);
        intent.putExtra("Setting", setting);
        context.startActivity(intent);
        ((Activity) context).finish();
    }
}

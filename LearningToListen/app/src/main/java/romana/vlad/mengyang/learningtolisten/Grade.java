package romana.vlad.mengyang.learningtolisten;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wumengyang on 07/03/16.
 */
public class Grade implements Serializable{
    private Setting.VoiceFrom playMode;
    private Setting.Mode gameMode;
    private ArrayList<Integer> difficultyArrayList;
    private ArrayList<Boolean> tfArrayList;

    public Grade(Setting.VoiceFrom playMode, Setting.Mode gameMode) {
        this.playMode = playMode;
        this.gameMode = gameMode;
        difficultyArrayList = new ArrayList<>();
        tfArrayList = new ArrayList<>();
    }

    public void addGrade(int grade, boolean result) {
        difficultyArrayList.add(grade);
        tfArrayList.add(result);
    }

    public void display() {
        Log.e("LAG", difficultyArrayList.toString());
    }

    public ArrayList<Boolean> getTfArrayList() {
        return tfArrayList;
    }

    public ArrayList<Integer> getDifficultyArrayList() {
        return difficultyArrayList;
    }
}

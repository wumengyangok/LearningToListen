package romana.vlad.mengyang.learningtolisten;

import java.io.Serializable;
import java.util.ArrayList;

public class Setting implements Serializable {

    public enum Mode {
        EASY, NORMAL, CRAZY
    }

    public enum VoiceFrom {
        LEFT, RIGHT, BOTH
    }

    public enum Animal {
        dog, cat, cow, pig, sheep, duck
    }

    public enum Speaker {
        Man, Woman, Child
    }

    private Mode mode;

    private VoiceFrom voiceFrom;

    private ArrayList<Animal> animalsArrayList;

    private ArrayList<Speaker> speakersArrayList;

    private int numberOfTrials;

    private String emailAddress;

    private String userName;

    Setting () {
        this.numberOfTrials = 8;
        this.mode = Mode.EASY;
        this.voiceFrom = VoiceFrom.BOTH;
        this.userName = "Bouncy";
        this.emailAddress = "wumengyang.ok@gmail.com";
        animalsArrayList = new ArrayList<>();
        speakersArrayList = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public int getNumberOfTrials() {
        return numberOfTrials;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Mode getMode() {
        return mode;
    }

    public VoiceFrom getVoiceFrom() {
        return voiceFrom;
    }

    public ArrayList<Animal> getAnimalsArrayList() {
        return animalsArrayList;
    }

    public ArrayList<Speaker> getSpeakersArrayList() {
        return speakersArrayList;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setVoiceFrom(VoiceFrom voiceFrom) {
        this.voiceFrom = voiceFrom;
    }

    public void addAnimalToList(Animal animal) {
        animalsArrayList.add(animal);
    }

    public void addSpeakerToList(Speaker speaker) {
        speakersArrayList.add(speaker);
    }

    public void deleteAnimalFromList(Animal animal) {
        animalsArrayList.remove(animal);
    }

    public void deleteSpeakerFromList(Speaker speaker) {
        speakersArrayList.remove(speaker);
    }

    public boolean ifInAnimalList(Animal animal) {
        return animalsArrayList.contains(animal);
    }

    public boolean ifInSpeakerList(Speaker speaker) {
        return speakersArrayList.contains(speaker);
    }
}
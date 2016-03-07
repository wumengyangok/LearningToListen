package romana.vlad.mengyang.learningtolisten;

import android.media.MediaPlayer;

public class AudioFile extends EasyModeActivity {

    private String fileName;
    private Setting.VoiceFrom playMode;
    private int volume;
    private final int delay = 3;
    private final float difference = 0.2f;

    AudioFile(String fileName, Setting.VoiceFrom playMode, int volume) {
        this.volume = volume;
        this.fileName = fileName;
        this.playMode = playMode;
    }

    public void play() {
//		int fileUri = getResources().getIdentifier(fileName.toString(), "raw", getPackageName());
//		fileUri = R.raw.duck_blue_8;
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

}
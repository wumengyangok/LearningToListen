package romana.vlad.mengyang.learningtolisten;

import android.content.Context;
import android.media.MediaPlayer;

import java.lang.reflect.Field;

/**
 * Created by wumengyang on 13/04/16.
 */
public class AudioFile {
    private Context context;
    private String fileName;
    private Setting.VoiceFrom playMode;
    private int volume;
    private final int delay = 10;
    private final float difference = 0.3f;

    public AudioFile(Context context, String fileName, Setting.VoiceFrom playMode, int volume) {
        this.context = context;
        this.volume = volume;
        this.fileName = fileName;
        this.playMode = playMode;
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

    // Play audio file with playMode
    public void play() {

        int fileUri = getResId(fileName, R.raw.class);
        MediaPlayer mediaPlayerLeft = MediaPlayer.create(context, fileUri);
        MediaPlayer mediaPlayerRight = MediaPlayer.create(context, fileUri);
        MediaPlayer mediaPlayer = MediaPlayer.create(context, fileUri);
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

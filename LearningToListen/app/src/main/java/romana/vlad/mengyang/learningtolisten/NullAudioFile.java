package romana.vlad.mengyang.learningtolisten;

import android.content.Context;
import android.util.Log;

/**
 * Created by wumengyang on 25/04/16.
 */
public class NullAudioFile extends AudioFile{
    public NullAudioFile(Context context, String fileName, Setting.VoiceFrom playMode, float volume) {
        super(context, fileName, playMode, volume);
    }

    @Override
    public boolean isFinished() {
        Log.e("TAG", "NULL");
        return false;
    }
}

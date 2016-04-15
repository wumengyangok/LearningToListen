package romana.vlad.mengyang.learningtolisten;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by wumengyang on 14/04/16.
 */
public class EasyGame extends Game {

    public EasyGame(Context context, Setting setting, ImageView imageView) {
        super(context, setting, imageView);
    }
    @Override
    public void run() {
        int animal = randomAvoid(numberAnimal, -1);
        int color = randomAvoid(numberColor, -1);
        int number = randomAvoid(numberInteger, -1);
        playTarget(animal, color, number);
        playMask(animal, color, number);
    }

}

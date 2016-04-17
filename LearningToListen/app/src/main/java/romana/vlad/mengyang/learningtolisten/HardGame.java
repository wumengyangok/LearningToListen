package romana.vlad.mengyang.learningtolisten;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * Created by wumengyang on 14/04/16.
 */
public class HardGame extends Game{

    private HashMap<String, String> prepared;

    private ArrayList<String> preparedColor;

    private ArrayList<Integer> preparedColorN;

    private ArrayList<String> preparedInteger;

    private ArrayList<Integer> preparedIntegerN;

    private final int size = 12;

    public HardGame(Context context, Setting setting, ImageView imageView) {
        super(context, setting, imageView);
    }

    private HardModeActivity view;

    public void randomWithoutRepetition() {
        Random random = new Random();
        int nColor = random.nextInt(numberColor);
        String color = colorArray[nColor];
        int nNumber = random.nextInt(numberInteger);
        String number = String.valueOf(numberArrayList.get(nNumber));
        while (prepared.containsValue(color + "_" + number)) {
            color = colorArray[random.nextInt(numberColor)];
            number = String.valueOf(numberArrayList.get(random.nextInt(numberInteger)));
        }
        prepared.put("hard_" + String.valueOf(prepared.size()), color + "_" + number);
        preparedColor.add(color);
        preparedInteger.add(number);
        preparedColorN.add(nColor);
        preparedIntegerN.add(nNumber);
    }

    public String getButtonName(String key) {
        Set<String> compare = prepared.keySet();
        Log.e("LAG", compare.toString());
        for (String s: compare) {
            if (key.contains(s))
                return prepared.get(s);
        }
        return null;
    }

    public void prepare() {
        prepared = new HashMap<>();
        preparedInteger = new ArrayList<>();
        preparedColor = new ArrayList<>();
        preparedColorN = new ArrayList<>();
        preparedIntegerN = new ArrayList<>();
        view = (HardModeActivity) context;
        for (int i = 0; i < size; i++) {
            randomWithoutRepetition();
            Button button = (Button) view.findViewById(getResId("hard_" + String.valueOf(i), R.id.class));
            button.setText(preparedInteger.get(i));
            Log.e("LAG", "button_" + preparedColor.get(i));
            button.setBackgroundResource(getResId("button_" + preparedColor.get(i), R.drawable.class));
        }
    }

    @Override
    public void run() {
        Random random = new Random();
        animal = randomAvoid(numberAnimal, -1);
        int randomOne = random.nextInt(size);
        int color = preparedColorN.get(randomOne);
        int number = preparedIntegerN.get(randomOne);
        int speaker = randomAvoid(numberSpeaker, -1);
        playTarget(animal, color, number, speaker);
        playMask(animal, color, number, speaker);
    }

}

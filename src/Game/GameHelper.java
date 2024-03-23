package Game;

import Panels.BricksBreaker;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameHelper {

    public static ArrayList<Integer> GenerateRandomBrickLocation(int count){
        ArrayList<Integer> randoms = new ArrayList<>();
        Random random = new Random();
        for (int i = 0 ;i < 6 ;i++){
            randoms.add(i);
        }
        int randomNumber;
        for (int i = 0 ;i < 6-count ;i++){
            randomNumber = random.nextInt(randoms.size());
            randoms.remove(randomNumber);
        }
        return randoms;
    }

    public static int Max(ArrayList<Integer> points) {
        int max = points.get(0);
        for (int i = 0 ;i < points.size() ;i++){
            if (points.get(i) > max){
                max = points.get(i);
            }
        }
        return max;
    }
}

package helper;

import java.util.Random;

public class RandomNameGenerator {
    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return "Guest" + String.format("%06d", number);
    }
}

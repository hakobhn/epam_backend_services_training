package com.epam.training.backend_services.avro_messaging.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RandomUtils {

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String getRandomSentence() {
        String[] name = {"Nicole", "Ronnie", "Robbie", "Alex", "Deb"};
        String[] action = {"liar", "driver", "cook", "speller", "sleeper", "cleaner", "soccer player"};

        // find out how many words there are in each list
        int nameLength = name.length;
        int actionLength = action.length;

        // Generate two random numbers
        int rand1 = (int) (Math.random() * nameLength);
        int rand2 = (int) (Math.random() * actionLength);

        String phrase1 = name[rand1];
        String phrase2 = action[rand2];

        return "It is obvious that" + ' ' + phrase1 + " " + "is a better" + " " +
                phrase2 + " " + "than" + " " + phrase1 + "!";
    }
}

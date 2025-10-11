package by.megatop.utils;

import java.util.Random;

public class EmailUtils {

    private static String generateRandomString(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        return random.ints(length, 0, chars.length())
                .mapToObj(chars::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    public static String generateRandomGmail() {
        return generateRandomString(7) + "@gmail.com";
    }
}

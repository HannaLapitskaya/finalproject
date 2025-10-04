package by.megatop.ui.utils;

import java.util.Random;
import java.util.function.Supplier;

public class LoginInfoUtils {

    public static final int PASSWORD_LENGTH = 10;
    public static final int PHONE_RANDOM_PART_LENGTH = 7;
    public static final String PHONE_BY_CODE = "375";
    private static final Random RANDOM = new Random();

    public static String generatePassword() {
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        Supplier<Object> generateNumberOrLetter = () -> {
            int index = RANDOM.nextInt(alphaNumericString.length());
            return alphaNumericString.charAt(index);
        };

        return generateString(PASSWORD_LENGTH, generateNumberOrLetter);
    }


    public static String generatePhoneNumberForUi() {
        return PHONE_BY_CODE + generatePhoneNumberForApi();
    }

    public static String generatePhoneNumberForApi() {
        Supplier<Object> generateNumberOrLetter = () -> RANDOM.nextInt(10);
        String phoneRandomPart = generateString(PHONE_RANDOM_PART_LENGTH, generateNumberOrLetter);
        int phoneRandomCodeIndex = RANDOM.nextInt(PhoneOperatorCode.values().length);
        return PhoneOperatorCode.values()[phoneRandomCodeIndex].getCode() + phoneRandomPart;
    }

    private static String generateString(int length, Supplier<Object> randomGenerator) {
        if (length <= 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(randomGenerator.get());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(generatePassword());
        System.out.println(generatePhoneNumberForUi());
        System.out.println(generatePhoneNumberForApi());
    }
}

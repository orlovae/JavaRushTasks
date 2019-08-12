package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) throws IOException  {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        while (!isTruePassword(sb.toString())) {
            if (sb.toString().length() >= 8) {
                sb.setLength(0);
            }

            for (int i = 0; i < 8; i++) {
                switch (getCharacters(random)) {
                    case 0:
                        sb.append(getUpperCaseCharacter(random));
                        break;
                    case 1:
                        sb.append(getLowerCaseCharacter(random));
                        break;
                    case 2:
                        sb.append(getDigit(random));
                        break;
                }
            }

        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        baos.write(sb.toString().getBytes());
        return baos;
    }

    private static int getCharacters(Random random){
        return random.nextInt(3);
    }

    private static char getUpperCaseCharacter(Random random){
        int min = 65;
        int max = 90;
        int diff = max - min;
        int result = random.nextInt(diff + 1);
        result += min;
        return (char) result;
    }

    private static char getLowerCaseCharacter(Random random){
        int min = 97;
        int max = 122;
        int diff = max - min;
        int result = random.nextInt(diff + 1);
        result += min;
        return (char) result;
    }

    private static char getDigit(Random random){
        int min = 48;
        int max = 57;
        int diff = max - min;
        int result = random.nextInt(diff + 1);
        result += min;
        return (char) result;
    }

    private static boolean isTruePassword(String passoword) {
        boolean isDigit = false;
        boolean isLowerCaseCharacter = false;
        boolean isUpperCaseCharacter = false;
        for (Character item:passoword.toCharArray()
             ) {
            if (Character.isDigit(item)) {
                isDigit = true;
            }

            if (Character.isLowerCase(item)) {
                isLowerCaseCharacter = true;
            }

            if (Character.isUpperCase(item)) {
                isUpperCaseCharacter = true;
                break;
            }
        }

        return isDigit && isLowerCaseCharacter && isUpperCaseCharacter;
    }
}
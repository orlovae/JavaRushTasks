package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'r', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "rr");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> wordList = new ArrayList<>();

        List<String> stringList = new ArrayList<>(Arrays.asList(words));

        for (String string:stringList
                ) {

            int lineEnd = crossword.length - 1;
            int columnEnd = crossword[0].length - 1;


            for (int line = 0; line <= lineEnd; line++) {
                for (int column = 0; column <= columnEnd; column++) {
                    if (crossword[line][column] == string.toCharArray()[0]) {

                        int right = column + string.length() - 1;
                        int down = line + string.length() - 1;
                        int left = column + 1 - string.length();
                        int up = line + 1 - string.length();

                        StringBuilder stringBuilder = new StringBuilder();

                        //1. Проверка выхода за пределы массива горизонтально вправо
                        if (right <= crossword[0].length - 1) {
                            //1.1 Взятие всего слова и перевод его в нормальное значение

                            for (int b = column; b <= right; b++) {
                                stringBuilder.append((char)crossword[line][b]);
                            }

                            Word word = createWord(stringBuilder, column, line, right, line);

                            if (isWord(stringBuilder, string)) {
                                wordList.add(word);
                            }

                            stringBuilder.setLength(0);
                        }

                            //2. Проверка выхода за пределы массива диагонально вниз вправо
                            if (right <= crossword[0].length - 1 & down <= crossword.length - 1) {
                                //2.1 Взятие всего слова и перевод его в нормальное значение

                                int a = line;
                                for (int b = column; b <= right; b++) {
                                    stringBuilder.append((char)crossword[a][b]);
                                    a++;
                                }

                                Word word = createWord(stringBuilder, column, line, right, down);

                                if (isWord(stringBuilder, string)) {
                                    wordList.add(word);
                                }

                                stringBuilder.setLength(0);
                            }

                            //3. Проверка выхода за пределы массива вертикально вниз
                            if (down <= crossword.length - 1) {
                                //3.1 Взятие всего слова и перевод его в нормальное значение

                                for (int b = line; b <= down; b++) {
                                    stringBuilder.append((char)crossword[b][column]);
                                }

                                Word word = createWord(stringBuilder, column, line, column, down);

                                if (isWord(stringBuilder, string)) {
                                    wordList.add(word);
                                }

                                stringBuilder.setLength(0);

                            }

                            //4. Проверка выхода за пределы массива диагонально вниз влево
                            if (left >= 0 & down <= crossword.length - 1) {

                                int a = line;
                                for (int b = column; left <= b; b--) {
                                    stringBuilder.append((char)crossword[a][b]);
                                    a++;
                                }

                                Word word = createWord(stringBuilder, column, line, left, down);

                                if (isWord(stringBuilder, string)) {
                                    wordList.add(word);
                                }

                                stringBuilder.setLength(0);
                            }

                            //5. Проверка выхода за пределы массива горизонтально влево
                            if (left >= 0) {
                                //5.1 Взятие всего слова и перевод его в нормальное значение

                                for (int b = column; b >= left; b--) {
                                    stringBuilder.append((char)crossword[line][b]);
                                }

                                Word word = createWord(stringBuilder, column, line, left, line);

                                if (isWord(stringBuilder, string)) {
                                    wordList.add(word);
                                }

                                stringBuilder.setLength(0);
                            }

                            //6. Проверка выхода за пределы массива диагонально вверх влево
                            if (left >= 0 & up >= 0) {

                            int a = line;
                            for (int b = column; b >= left; b--) {
                                stringBuilder.append((char)crossword[a][b]);
                                a--;
                            }

                            Word word = createWord(stringBuilder, column, line, left, up);

                            if (isWord(stringBuilder, string)) {
                                wordList.add(word);
                            }

                            stringBuilder.setLength(0);
                            }

                            //7. Проверка выхода за пределы массива вертикально вверх
                            if (up >= 0) {
                                //7.1 Взятие всего слова и перевод его в нормальное значение

                                for (int b = line; up <= b; b--) {
                                    stringBuilder.append((char)crossword[b][column]);
                                }

                                Word word = createWord(stringBuilder, column, line, column, up);

                                if (isWord(stringBuilder, string)) {
                                    wordList.add(word);
                                }

                                stringBuilder.setLength(0);
                            }

                            //8. Проверка выхода за пределы массива диагонально вверх вправо
                            if (up >= 0 & right <= crossword[0].length - 1) {
                                //8.1 Проверка следующей буквы

                                int a = line;
                                for (int b = column; b <= right; b++) {
                                    stringBuilder.append((char)crossword[a][b]);
                                    a--;
                                }

                                Word word = createWord(stringBuilder, column, line, right, up);

                                if (isWord(stringBuilder, string)) {
                                    wordList.add(word);
                                }

                                stringBuilder.setLength(0);

                            }
                        }
                    }
                }
            }

        for (Word w:wordList
             ) {
            System.out.println(w);
        }

        return wordList;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }

    public static boolean isWord (StringBuilder stringBuilder, String word) {
        boolean isWord = false;

        if (stringBuilder.toString().equals(word)) {
            isWord = true;
        }

        return isWord;
    }

    public static Word createWord (StringBuilder stringBuilder, int startI, int startJ,
                                   int endI, int endJ) {
        Word word = new Word(stringBuilder.toString());
        word.setStartPoint(startI, startJ);
        word.setEndPoint(endI, endJ);

        return word;
    }
}

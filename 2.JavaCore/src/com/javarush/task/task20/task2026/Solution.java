package com.javarush.task.task20.task2026;

import java.util.ArrayList;
import java.util.List;

/*
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0, 1, 0},
                {1, 1, 0, 0, 1, 0},
                {1, 1, 0, 0, 0, 0},
                {1, 1, 0, 1, 1, 0}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        List<Rectangle> rectangleList = new ArrayList<>();

        int lineEnd = a.length - 1;
        int columnEnd = a[0].length - 1;

        for (int line = 0; line <= lineEnd; line++) {
            for (int column = 0; column <= columnEnd; column++) {

                if (a[line][column] == 1) {
                    Point leftUpPoint = new Point(line, column);
                    Rectangle rectangle = createRectangle(a, leftUpPoint);
                    eraseRectangle(a, rectangle);
                    rectangleList.add(rectangle);

                }
            }
        }
        return rectangleList.size();
    }

    public static void eraseRectangle(byte[][]a, Rectangle rectangle) {
        int lineEnd = rectangle.rightDownPoint.getX();
        int columnEnd = rectangle.rightDownPoint.getY();

        for (int line = rectangle.leftUpPoint.getX(); line <= lineEnd; line++) {
            for (int column = rectangle.leftUpPoint.getY(); column <= columnEnd; column++) {
                a[line][column] = 0;
            }
        }
    }

    public static Rectangle createRectangle(byte[][] a, Point leftUpPoint) {
        int lineEnd = a.length - 1;
        int columnEnd = a[0].length - 1;

        for (int line = leftUpPoint.getX(); line <= lineEnd; line++) {
            int lineNext = line + 1;

            for (int column = leftUpPoint.getY(); column <= columnEnd; column++) {
                int columnNext = column + 1;

                if (lineNext <= lineEnd & columnNext <= columnEnd) {

                    if (a[line][column] == 1) {
                        if (a[lineNext][column] == 0 & a[line][columnNext] == 0) {
                            Point rightDownPoint = new Point(line, column);
                            return new Rectangle(leftUpPoint, rightDownPoint);
                        }
                    } else {
                        break;
                    }

                } else {
                    if (lineNext > lineEnd) {
                        for (int c = column; c <= columnEnd; c++) {
                            if (a[line][c] == 0) {
                                Point rightDownPoint = new Point(line, c - 1);
                                return new Rectangle(leftUpPoint, rightDownPoint);
                            }
                        }
                    }

                    if (columnNext > columnEnd) {
                        if (a[line][columnEnd] == 1) {
                            Point rightDownPoint = new Point(line, columnEnd);
                            return new Rectangle(leftUpPoint, rightDownPoint);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static class Rectangle {
        private Point leftUpPoint;
        private Point rightDownPoint;

        public Rectangle(Point leftUpPoint, Point rightDownPoint) {
            this.leftUpPoint = leftUpPoint;
            this.rightDownPoint = rightDownPoint;
        }

        @Override
        public String toString() {
            return "Rectangle{" +
                    "leftUpPoint=" + leftUpPoint.toString() +
                    ", rightDownPoint=" + rightDownPoint.toString() +
                    '}';
        }
    }

    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}

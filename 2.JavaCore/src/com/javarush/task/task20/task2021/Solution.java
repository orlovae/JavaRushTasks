package com.javarush.task.task20.task2021;

import java.io.*;

/* 
Сериализация под запретом
*/
public class Solution implements Serializable {

    public static class SubSolution extends Solution {
        private void writeObject(ObjectOutputStream outputStream) throws IOException {
            throw new NotSerializableException("Not Serializable!!!");
        }

        private void readObject(ObjectInputStream inputStream) throws IOException {
            throw new NotSerializableException("Not Serializable!!!");
        }
    }

    public static void main(String[] args) throws IOException {
        SubSolution subSolution = new SubSolution();

        FileOutputStream fileOutputStream = new FileOutputStream("file.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(subSolution);

        objectOutputStream.close();

    }
}

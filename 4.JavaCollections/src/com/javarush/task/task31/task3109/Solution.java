package com.javarush.task.task31.task3109;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        String endsWith = fileName.substring(fileName.length() - 3,
                fileName.length());
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(fileName);) {
            switch (endsWith) {
                case "xml":
                    properties.loadFromXML(fis);
                    return properties;
                default:
                    properties.load(fis);
                    return properties;
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }

        return properties;
    }


}

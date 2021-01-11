package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "";
        String login = "";
        String password = "";
        try (BufferedReader br = new BufferedReader(new FileReader("app.properties"));
             BufferedReader brPass = new BufferedReader(new FileReader(".idea/pass.txt"))) {
            List<String> list = new ArrayList<>();
            br.lines().forEach(list::add);
            brPass.lines().forEach(s -> list.add(s));
            for (String s: list) {
                if (s.contains("dburl")) {
                    url = s.split("=")[1];
                }
                if (s.contains("dblogin")) {
                    login = s.split("=")[1];
                }
                if (s.contains("dbpassword")) {
                    password = s.split("=")[1];
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}

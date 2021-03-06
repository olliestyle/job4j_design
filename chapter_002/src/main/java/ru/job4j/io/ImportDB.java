package ru.job4j.io;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties config;
    private String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dump))) {
            br.lines().forEach(user -> {
                String[] s = user.split(";");
                if (s.length == 2) {
                    users.add(new User(s[0], s[1]));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection con = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            try (Statement st = con.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS public.spammers(id serial primary key, name char(50) not null, email char(50) not null)");
            }
            for (User user: users) {
                try (PreparedStatement ps = con.prepareStatement("insert into spammer.public.spammers (name, email) values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.email = email;
            this.name = name;
        }

        public static void main(String[] args) throws Exception {
            Properties config = new Properties();
            try (FileInputStream fis = new FileInputStream("./app.properties")) {
                config.load(fis);
            }
            ImportDB db = new ImportDB(config, "./dump.txt");
            db.save(db.load());
        }
    }
}

package ru.job4j.serial;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class UserJSON {

    int age;
    boolean isWork;
    String name;
    School school;
    Relative[] relatives;

    public UserJSON(int age, boolean isWork, String name, School school, Relative[] relatives) {
        this.age = age;
        this.isWork = isWork;
        this.name = name;
        this.school = school;
        this.relatives = relatives;
    }

    public static class Relative {

        private String relative;

        public Relative(String relative) {
            this.relative = relative;
        }
    }

    public static class School {
        private int number;
        private boolean isGraduate;

        public School(int number, boolean isGraduate) {
            this.number = number;
            this.isGraduate = isGraduate;
        }
    }

    @Override
    public String toString() {
        return "UserJSON{" +
                "age=" + age +
                ", isWork=" + isWork +
                ", name='" + name + '\'' +
                ", school=" + school +
                ", relatives=" + Arrays.toString(relatives) +
                '}';
    }

    public static void main(String[] args) {
        final UserJSON userJSON = new UserJSON(25,
                true,
                "Ivan",
                new School(111, true),
                new Relative[]{new Relative("sister"), new Relative("brother")});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(userJSON));

        final String myUserJson = "{\"age\":25," +
                "\"isWork\":true," +
                "\"name\":\"Jack\"," +
                "\"school\":" +
                "{\"number\":222,\"isGraduate\":false}," +
                "\"relatives\":[{\"relative\":\"mother\"},{\"relative\":\"father\"}]" +
                "}";

        final UserJSON myUserJSON = gson.fromJson(myUserJson, UserJSON.class);
        System.out.println(myUserJson);
    }
}

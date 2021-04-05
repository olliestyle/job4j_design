package ru.job4j.serial;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserJSON {

    private int age;
    private boolean isWork;
    private String name;
    private School school;
    private Relative[] relatives;

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

        public String getRelative() {
            return relative;
        }

        @Override
        public String toString() {
            return "Relative{" +
                    "relative='" + relative + '\'' +
                    '}';
        }

        public String toJsonString() {
            return "{\"relative\":" + relative + "}";
        }

    }

    public static class School {
        private int number;
        private boolean isGraduate;

        public School(int number, boolean isGraduate) {
            this.number = number;
            this.isGraduate = isGraduate;
        }

        public int getNumber() {
            return number;
        }

        public boolean isGraduate() {
            return isGraduate;
        }

        @Override
        public String toString() {
            return "School{" +
                    "number=" + number +
                    ", isGraduate=" + isGraduate +
                    '}';
        }

        public String toJsonString() {
            return "{\"number\":" + number + ",\"isGraduate\":" + isGraduate + "}";
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
                new Relative[]{new Relative("sister"), new Relative("brother"), new Relative("mother")});

        JSONObject userJSONObject = new JSONObject();
        userJSONObject.put("age", userJSON.age);
        userJSONObject.put("isWork", userJSON.isWork);
        userJSONObject.put("name", userJSON.name);
        JSONObject jsonSchool = new JSONObject(userJSON.school);
        userJSONObject.put("school", jsonSchool);

        JSONArray relative = new JSONArray();
        for (Relative r: userJSON.relatives) {
            relative.put(new JSONObject(r));
        }
        userJSONObject.put("relatives", relative);

        System.out.println(userJSONObject.toString());

//        final UserJSON userJSON = new UserJSON(25,
//                true,
//                "Ivan",
//                new School(111, true),
//                new Relative[]{new Relative("sister"), new Relative("brother")});
//        final Gson gson = new GsonBuilder().create();
//        System.out.println(gson.toJson(userJSON));
//
//        final String myUserJson = "{\"age\":25," +
//                "\"isWork\":true," +
//                "\"name\":\"Jack\"," +
//                "\"school\":" +
//                "{\"number\":222,\"isGraduate\":false}," +
//                "\"relatives\":[{\"relative\":\"mother\"},{\"relative\":\"father\"}]" +
//                "}";
//
//        final UserJSON myUserJSON = gson.fromJson(myUserJson, UserJSON.class);
//        System.out.println(myUserJson);
    }
}

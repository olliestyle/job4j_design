package ru.job4j.serial;

/**
 * <?xml version="1.1" encoding="UTF-8" ?>
 * <userXML age="25" isWork="true" name="Ivan">
 *      <school number="222" isGraduate="true"/>
 *      <relatives>
 *          <relative>brother<relative/>
 *          <relative>sister<relative/>
 *      <relatives/>
 * <userXML/>
 */

public class UserXML {

    private int age;
    private boolean isWork;
    private String name;
    private School school;
    private Relative[] relatives;

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
}

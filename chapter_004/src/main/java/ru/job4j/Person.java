package ru.job4j;

public class Person {
    private int age;
    private String name;
    private Person person;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person(int age, String name, Person person) {
        this.age = age;
        this.name = name;
        this.person = person;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s%n", age, name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

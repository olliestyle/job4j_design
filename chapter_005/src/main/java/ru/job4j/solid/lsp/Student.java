package ru.job4j.solid.lsp;

public class Student {

    protected float averageScore;

    public int payStipend(int averageScore) {
        if (averageScore < 4) {
            throw new IllegalArgumentException("Average score is low");
        }
        return averageScore * 1000;
    }
}

class RussianStudent extends Student {

    public int payStipend(int averageScore) {
        return averageScore * 1000;
    }
}

class SecondRuleExample {

    public static void main(String[] args) {
        Student student = new RussianStudent();
        System.out.println(student.payStipend(3));
    }

}

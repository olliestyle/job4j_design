package ru.job4j.solid.isp.menu;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private String paragraph;
    private List<Task> subTasks;

    public Task(String paragraph) {
        this.paragraph = paragraph;
        subTasks = new ArrayList<>();
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }

    @Override
    public String toString() {
        String rsl = "";
        rsl = paragraph + System.lineSeparator();
        for (Task task: subTasks) {
            rsl = rsl + task.toString();
        }
        return rsl;
    }
}

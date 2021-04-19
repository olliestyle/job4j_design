package ru.job4j.solid.isp.menu;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private String paragraph;
    private List<Task> subTasks;
    private UserAction userAction;

    public Task(String paragraph, UserAction userAction) {
        this.paragraph = paragraph;
        this.userAction = userAction;
        subTasks = new ArrayList<>();
    }

    public UserAction getUserAction() {
        return userAction;
    }

    public String getParagraph() {
        return paragraph;
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

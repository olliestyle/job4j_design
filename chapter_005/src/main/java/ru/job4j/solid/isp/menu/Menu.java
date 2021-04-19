package ru.job4j.solid.isp.menu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Menu<T extends Task> implements CreateMenu<T>, ShowMenu {

    List<Task> taskList = new ArrayList<>();

    @Override
    public void addTask(Task task) {
        taskList.add(task);
    }

    @Override
    public void addSubTask(Task parent, Task child) {
        parent.getSubTasks().add(child);
    }

    @Override
    public void printMenu() {
        for (Task task: taskList) {
            System.out.print(task);
        }
    }

    public Task choose(String paragraph) {
        Task res = null;
        List<Task> temp = getTaskList();
        for (Task task: temp) {
            if(task.getParagraph().equals(paragraph)) {
                res = task;
            }
        }
        return res;
    }

    private List<Task> getTaskList() {
        List<Task> res = new ArrayList<>();
        Queue<Task> taskQueue = new LinkedList<>(taskList);
        Task polled = taskQueue.poll();
        while (polled != null) {
            res.add(polled);
            taskQueue.addAll(polled.getSubTasks());
            polled = taskQueue.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        Task task = new Task("1. hello", new UserActionFirst());
        Task subTask = new Task("1.1 foo", new UserActionFirst());
        Task subSubTask = new Task("1.1.1 bar", new UserActionFirst());
        menu.addTask(task);
        menu.addSubTask(task, subTask);
        menu.addSubTask(subTask, subSubTask);
        Task task2 = new Task("2. Biz", new UserActionSecond());
        Task subTask2 = new Task("2.1 foo2", new UserActionSecond());
        Task subSubTask2 = new Task("2.1.1 bar2", new UserActionSecond());
        Task subTask22 = new Task("2.2 foo22", new UserActionSecond());
        menu.addTask(task2);
        menu.addSubTask(task2, subTask2);
        menu.addSubTask(task2, subTask22);
        menu.addSubTask(subTask2, subSubTask2);
        Task task3 = new Task("3", new UserActionThird());
        Task subTask31 = new Task("3.1", new UserActionThird());
        Task subSubTask311 = new Task("3.1.1", new UserActionThird());
        Task subSubTask312 = new Task("3.1.2", new UserActionThird());
        Task subTask32 = new Task("3.2", new UserActionThird());
        Task subSubTask321 = new Task("3.2.1", new UserActionThird());
        Task subSubTask322 = new Task("3.2.2", new UserActionThird());
        Task subSubTask3221 = new Task("3.2.2.1", new UserActionThird());
        menu.addTask(task3);
        menu.addSubTask(task3, subTask31);
        menu.addSubTask(task3, subTask32);
        menu.addSubTask(subTask31, subSubTask311);
        menu.addSubTask(subTask31, subSubTask312);
        menu.addSubTask(subTask32, subSubTask321);
        menu.addSubTask(subTask32, subSubTask322);
        menu.addSubTask(subSubTask322, subSubTask3221);
        menu.printMenu();
        menu.choose("3.1.1").getUserAction().doAction();
        menu.choose("2.1 foo2").getUserAction().doAction();
        menu.choose("1.1.1 bar").getUserAction().doAction();
    }
}

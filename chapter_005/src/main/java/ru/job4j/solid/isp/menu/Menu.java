package ru.job4j.solid.isp.menu;

import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
        Menu menu = new Menu();
        Task task = new Task("1. hello");
        Task subTask = new Task("1.1 foo");
        Task subSubTask = new Task("1.1.1 bar");
        menu.addTask(task);
        menu.addSubTask(task, subTask);
        menu.addSubTask(subTask, subSubTask);
        Task task2 = new Task("2. Biz");
        Task subTask2 = new Task("2.1 foo2");
        Task subSubTask2 = new Task("2.1.1 bar2");
        Task subTask22 = new Task("2.2 foo22");
        menu.addTask(task2);
        menu.addSubTask(task2, subTask2);
        menu.addSubTask(task2, subTask22);
        menu.addSubTask(subTask2, subSubTask2);
        Task task3 = new Task("3");
        Task subTask31 = new Task("3.1");
        Task subSubTask311 = new Task("3.1.1");
        Task subSubTask312 = new Task("3.1.2");
        Task subTask32 = new Task("3.2");
        Task subSubTask321 = new Task("3.2.1");
        Task subSubTask322 = new Task("3.2.2");
        Task subSubTask3221 = new Task("3.2.2.1");
        menu.addTask(task3);
        menu.addSubTask(task3, subTask31);
        menu.addSubTask(task3, subTask32);
        menu.addSubTask(subTask31, subSubTask311);
        menu.addSubTask(subTask31, subSubTask312);
        menu.addSubTask(subTask32, subSubTask321);
        menu.addSubTask(subTask32, subSubTask322);
        menu.addSubTask(subSubTask322, subSubTask3221);
        menu.printMenu();
    }
}

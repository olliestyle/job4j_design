package ru.job4j.solid.isp.menu;

public class UserActionFirst implements UserAction {
    @Override
    public void doAction() {
        System.out.println("action first");
    }
}

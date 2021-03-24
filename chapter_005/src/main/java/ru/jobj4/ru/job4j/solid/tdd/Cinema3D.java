package ru.jobj4.ru.job4j.solid.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {
    @Override
    public List<Session> find(Predicate<Session> filter) {
        return null;
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        return null;
    }

    @Override
    public void add(Session session) {

    }

//    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2021, 3, 15, 21, 53);
//        System.out.println(calendar.get(1)); // year
//        System.out.println(calendar.get(2)); // month
//        System.out.println(calendar.get(5)); // date
//        System.out.println(calendar.get(11)); // hours
//        System.out.println(calendar.get(12)); // minute
//    }
}

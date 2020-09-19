package ru.job4j.model;

import java.util.*;

public class User<T> {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public static void main(String[] args) {

//        User user1 = new User("Murat", 2, new GregorianCalendar(1989, 3, 26));
//        User user2 = new User("Murat", 3, new GregorianCalendar(1989, 3, 26));
//        User user3 = new User("Murat", 4, new GregorianCalendar(1989, 3, 26));
//        User user4 = new User("Murat", 5, new GregorianCalendar(1989, 3, 26));
//        User user5 = new User("Murat", 6, new GregorianCalendar(1989, 3, 26));
//        User user6 = new User("Murat", 7, new GregorianCalendar(1989, 3, 26));
//        User user7 = new User("Murat", 8, new GregorianCalendar(1989, 3, 26));
//        User user8 = new User("Murat", 9, new GregorianCalendar(1989, 3, 26));
//        User user9 = new User("Murat", 21, new GregorianCalendar(1989, 3, 26));
//        User user10 = new User("Murat", 22, new GregorianCalendar(1989, 3, 26));
//        User user11 = new User("Murat", 23, new GregorianCalendar(1989, 3, 26));
//        User user12 = new User("Murat", 24, new GregorianCalendar(1989, 3, 26));
//        User user13 = new User("Murat", 25, new GregorianCalendar(1989, 3, 26));
//        User user14 = new User("Murat", 26, new GregorianCalendar(1989, 3, 26));
//        Map<User, String> map = new HashMap<>();
//        map.put(user1, "Hello");
//        map.put(user2, "Hi");
//        map.put(user3, "Hia");
//        map.put(user4, "Hib");
//        map.put(user5, "Hic");
//        map.put(user6, "Hid");
//        map.put(user7, "Hie");
//        map.put(user8, "Hit");
//        map.put(user9, "Hiy");
//        map.put(user10, "Hui");
//        map.put(user11, "Hio");
//        map.put(user12, "Hip");
//        map.put(user13, "Hil");
//        map.put(user14, "Hiz");
//        map.put(null, "hello");
//        System.out.println(map);
//        System.out.println(map.get(user1));
//        System.out.println(map.get(user2));
//        System.out.println(map.get(null));
//        System.out.println(map.get(user3));
//        System.out.println(map.get(user4));
//        System.out.println(map.get(user5));
//        System.out.println(map.get(user6));
//        System.out.println(map.get(user7));
//        System.out.println(map.get(user8));
//        System.out.println(map.get(user9));
//        System.out.println(map.get(user10));
//        System.out.println(map.get(user11));
//        System.out.println(map.get(user12));
//        System.out.println(map.get(user13));
//        System.out.println(map.get(user14));
    }

}

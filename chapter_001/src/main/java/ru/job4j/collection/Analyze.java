package ru.job4j.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Analyze {

    public static Info diff(List<User> previous, List<User> current) {

        Info info = new Info(0, 0, 0);
        Map<Integer, String> previousMap = new HashMap<>();
        Map<Integer, String> currentMap = new HashMap<>();

        for (User user: previous) {
            previousMap.put(user.id, user.name);
        }

        for (User user: current) {
            currentMap.put(user.id, user.name);
        }

        // Эту проверку можно исключить, но без неё зря будут работать следующие циклы, а с ней получается два return'а в методе.
        // Что хуже - читаемость кода или производительность ?
        if (Objects.equals(previousMap.entrySet(), currentMap.entrySet())) {
            return info;
        }

        for (Integer i: previousMap.keySet()) {
            if (currentMap.containsKey(i)) {
                if (!Objects.equals(currentMap.get(i), previousMap.get(i))) {
                    info.changed++;
                }
            } else {
                info.deleted++;
            }
        }

        for (Integer i: currentMap.keySet()) {
            if (!previousMap.containsKey(i)) {
                info.added++;
            }
        }
        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}

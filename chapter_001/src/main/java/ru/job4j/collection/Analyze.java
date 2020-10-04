package ru.job4j.collection;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Analyze {

    public static Info diff(List<User> previous, List<User> current) {

        Info info = new Info(0, 0, 0);
        Map<Integer, String> currentMap = current.stream().collect(Collectors.toMap(user -> user.id, user -> user.name));

        for (User user: previous) {
            if (currentMap.containsKey(user.id)) {
                if (!Objects.equals(user.name, currentMap.get(user.id))) {
                    info.changed++;
                }
                currentMap.remove(user.id);
            } else {
                info.deleted++;
            }
        }

        info.added = currentMap.size();

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

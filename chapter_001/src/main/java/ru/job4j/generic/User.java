package ru.job4j.generic;

import java.util.Objects;

public class User extends Base {

    private String name;

    protected User(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        User user = (User) obj;
        return Objects.equals(user.name, this.name) && Objects.equals(user.getId(), this.getId());
    }

    @Override
    public String toString() {
        return getId() + " " + name + " ";
    }
}

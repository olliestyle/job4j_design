package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    private int findIndexById(String id) {
//        for (T t: mem) {
//            if (t.getId().equals(id)) {
//                return mem.indexOf(t);
//            }
//        }
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
//        for (T t: mem) {
//            if (t.getId().equals(id)) {
//                mem.set(mem.indexOf(t), model);
//                return true;
//            }
//        }
        int res = findIndexById(id);
        if (res != -1) {
            mem.set(res, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        for (T t: mem) {
            if (t.getId().equals(id)) {
                mem.remove(t);
                return true;
            }
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (T t: mem) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mem);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        MemStore<T> memStore = (MemStore<T>) obj;
        return this.mem.equals(memStore.mem);
    }

    @Override
    public String toString() {
        String result = "";
        for (T t: mem) {
            result += t.getId() + " ";
        }
        return result;
    }
}

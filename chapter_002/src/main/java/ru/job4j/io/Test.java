package ru.job4j.io;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Objects;

public class Test implements Serializable {

    int i;
    String string;

    public Test(int i, String string) {
        this.i = i;
        this.string = string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Test test = (Test) o;
        return i == test.i
                && Objects.equals(string, test.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, string);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(out);
        Test test = new Test(3, "hello");
        outputStream.writeObject(test);
        byte[] data = out.toByteArray();
        DatagramPacket packet = new DatagramPacket(data, data.length);
        byte[] data2 = packet.getData();
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data2));
        Test test2 = (Test) objectInputStream.readObject();
        System.out.println(test.equals(test2));
    }
}

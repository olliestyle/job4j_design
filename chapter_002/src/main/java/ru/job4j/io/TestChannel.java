package ru.job4j.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestChannel {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        String s = "toCopy.txt";
        Path path = Paths.get(s);
        FileOutputStream file = new FileOutputStream("afterCopy.txt");
        Files.copy(path, file);
        FileInputStream fis = new FileInputStream("toCopy.txt");
        FileChannel channel = fis.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        int r = channel.read(byteBuffer);
        while (r != -1) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                byteBuffer.get();
            }
            byteBuffer.clear();
            r = channel.read(byteBuffer);
        }


    }
}

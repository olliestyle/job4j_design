package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class HttpsTest {
    public static void main(String[] args) throws IOException {
        URLConnection connection = new URL("https://images.wallpaperscraft.ru/image/doroga_zakat_sumerki_151237_1366x768.jpg").openConnection();

        InputStream is = connection.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        int b;
        while ((b = bis.read()) != -1) {
            System.out.print((char) b);
        }
    }
}

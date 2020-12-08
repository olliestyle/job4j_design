package ru.job4j.io;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TestHTTPClient {

    public static void main(String[] args) throws IOException {

        InetAddress inetAddress = InetAddress.getByName("www.webcode.me");
        try (Socket socket = new Socket(inetAddress, 80)) {
            BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
//            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
            out.write("GET / HTTP/1.1\nHost: webcode.me\n\n".getBytes());
            out.flush();
            int b;
            while ((b = in.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

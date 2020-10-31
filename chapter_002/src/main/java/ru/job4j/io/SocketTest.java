package ru.job4j.io;

import java.io.*;
import java.net.Socket;

public class SocketTest {
    public static void main(String[] args) {
        try {
            // Connect to the server
            Socket socket = new Socket("webcode.me", 80);
            System.out.println("SocketTestClient " + socket.getInetAddress() + " " + socket.getPort() + " " + socket.getLocalPort());

            // Create input and output streams to read from and write to the server
            PrintStream out = new PrintStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Follow the HTTP protocol of GET <path> HTTP/1.0 followed by an empty line
            out.println("GET / HTTP/1.1");
            out.println("Host: www.webcode.me");
            out.println("");
            out.flush();
//            out.println();
//            out.println("ololo");
//            out.println("BYE");

            // Read data from the server until we finish reading the document
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            // Close our streams
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

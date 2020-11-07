package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    /** @noinspection checkstyle:InnerAssignment, checkstyle:InnerAssignment */
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(15656)) {
            boolean done = false;
            while (!done) {
                Socket clientSocket = serverSocket.accept();
                try (OutputStream outputStream = clientSocket.getOutputStream();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String str;
                    while (!(str = bufferedReader.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.equals("GET /?msg=Bye HTTP/1.1")) {
                            done = true;
                            break;
                        }
                    }
                    outputStream.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

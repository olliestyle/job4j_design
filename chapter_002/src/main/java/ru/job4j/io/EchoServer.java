package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(15656)) {
            boolean done = false;
            while (!done) {
                Socket clientSocket = serverSocket.accept();
                try (OutputStream outputStream = clientSocket.getOutputStream();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String str = bufferedReader.readLine();
                    String answer = "";
                    while (!str.isEmpty()) {
                        System.out.println(str);
                        if (str.equals("GET /?msg=Exit HTTP/1.1")) {
                            done = true;
                            break;
                        } else if (str.startsWith("GET")) {
                            answer = str.substring(str.indexOf("=") + 1, str.indexOf(" H"));
                        }
                        str = bufferedReader.readLine();
                    }
                    outputStream.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    outputStream.write(answer.getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

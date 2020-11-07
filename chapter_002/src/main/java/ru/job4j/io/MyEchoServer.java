package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyEchoServer {
    // Создать объект типа Socket на стороне клиента
    // и воссоздать его с помощью ServerSocket на стороне сервера – вот необходимый минимум для соединения.
    public static void main(String[] args) throws IOException {
        // установить сокет на стороне сервера
        try (ServerSocket serverSocket = new ServerSocket(15666)) {
            // ожидать подключение клиента
            try (Socket incoming = serverSocket.accept()) {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();

                try (Scanner in = new Scanner(inStream)) {
                    PrintWriter out = new PrintWriter(outStream, true);
                    out.println("ServerSocket Info: " + serverSocket.getInetAddress() + " " + serverSocket.getLocalPort());
                    out.println("Incoming Client socket info: " + incoming.getInetAddress() + " " + incoming.getPort() + " " + incoming.getLocalPort());
                    //передаём обратно данные, полученные от клиента
                    boolean done = false;
                    while (!done && in.hasNextLine()) {
                        String line = in.nextLine();
                        System.out.println("Мы на сервере");
                        out.println(line);
                        if (line.trim().equals("BYE")) {
                            done = true;
                        }
                    }
                }
            }
        }
    }
}

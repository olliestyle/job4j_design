package ru.job4j.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ThreadedEchoServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(20001)) {
            int i = 1;
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("client number " + i);
                i++;
                Runnable runnable = new ThreadEchoHandler(clientSocket);
                Thread t = new Thread(runnable);
                t.start();
                System.out.println(t.getId());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadEchoHandler implements Runnable {

    private final Socket clientSocket;

    public ThreadEchoHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
       try (clientSocket) {
           InputStream inputStream = clientSocket.getInputStream();
           OutputStream outputStream = clientSocket.getOutputStream();

           Scanner in = new Scanner(inputStream);
           PrintWriter out = new PrintWriter(outputStream, true); // автоматическая очистка
           out.println("Hello. Enter BYE to exit");
           boolean done = false;
           while (!done && in.hasNextLine()) {
               String line = in.nextLine();
               out.println("Echo " + line);
               if (line.trim().equals("BYE")) {
                   done = true;
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}


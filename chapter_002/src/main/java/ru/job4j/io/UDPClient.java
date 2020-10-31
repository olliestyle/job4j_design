package ru.job4j.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    public static void main(String[] args) {
//        String hostname = "djxmmx.net"; port - 17
        String hostname = "localhost";
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(out)) {
            Test test = new Test(3, "hello");
            outputStream.writeObject(test);
            byte[] data = out.toByteArray();
            InetAddress address = InetAddress.getByName(hostname);
            DatagramSocket socket = new DatagramSocket();

            while (true) {
                DatagramPacket request = new DatagramPacket(data, data.length, address, 11111);
                socket.send(request);
                byte[] buffer = new byte[1024];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);
                String quote = new String(buffer, 0, response.getLength());

                System.out.println(quote);
                System.out.println();

                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

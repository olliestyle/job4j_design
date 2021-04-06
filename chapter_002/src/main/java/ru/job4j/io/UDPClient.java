package ru.job4j.io;

import de.javawi.jstun.attribute.ChangeRequest;
import de.javawi.jstun.attribute.MappedAddress;
import de.javawi.jstun.attribute.MessageAttribute;
import de.javawi.jstun.attribute.MessageAttributeParsingException;
import de.javawi.jstun.header.MessageHeader;
import de.javawi.jstun.util.UtilityException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {

    DatagramSocket socket;

    public UDPClient() throws SocketException {
        socket = new DatagramSocket();
        socket.setReuseAddress(true);
    }

    public static void main(String[] args) {
//        String hostname = "djxmmx.net"; port - 17
//        String hostname = "localhost";
        String hostname = "5.8.39.124";
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(out)) {
            UDPClient udpClient = new UDPClient();
            Test test = new Test(3, "hello");
            outputStream.writeObject(test);
            byte[] data = out.toByteArray();
            InetAddress address = InetAddress.getByName(hostname);
            udpClient.myIPAndPort();

            while (true) {
//                DatagramPacket request = new DatagramPacket(data, data.length, address, 11111);
                DatagramPacket request = new DatagramPacket(data, data.length, address, 7741);
                udpClient.socket.send(request);
                byte[] buffer = new byte[1024];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                udpClient.socket.receive(response);
                String quote = new String(buffer, 0, response.getLength());

                System.out.println(quote);
                System.out.println();

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void myIPAndPort() throws IOException, UtilityException {
        MessageHeader sendMH = new MessageHeader(MessageHeader.MessageHeaderType.BindingRequest);
        // sendMH.generateTransactionID();

        // add an empty ChangeRequest attribute. Not required by the
        // standard,
        // but JSTUN server requires it

        ChangeRequest changeRequest = new ChangeRequest();
        sendMH.addMessageAttribute(changeRequest);

        byte[] data = sendMH.getBytes();
        socket.setReuseAddress(true);

        DatagramPacket p = new DatagramPacket(data, data.length, InetAddress.getByName("stun.b2b2c.ca"), 3478);
        socket.send(p);

        DatagramPacket rp;

        rp = new DatagramPacket(new byte[512], 512);

        socket.receive(rp);
        MessageHeader receiveMH = new MessageHeader(MessageHeader.MessageHeaderType.BindingResponse);
        // System.out.println(receiveMH.getTransactionID().toString() + "Size:"
        // + receiveMH.getTransactionID().length);
        try {
            receiveMH.parseAttributes(rp.getData());
        } catch (MessageAttributeParsingException e) {
            e.printStackTrace();
        }
        MappedAddress ma = (MappedAddress) receiveMH
                .getMessageAttribute(MessageAttribute.MessageAttributeType.MappedAddress);
        System.out.println(ma.getAddress() + " " + ma.getPort());
        System.out.println(socket.getInetAddress() + " " + socket.getPort() + " " + socket.getLocalPort());
    }
}

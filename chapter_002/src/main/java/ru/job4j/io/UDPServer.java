package ru.job4j.io;

import de.javawi.jstun.attribute.ChangeRequest;
import de.javawi.jstun.attribute.MappedAddress;
import de.javawi.jstun.attribute.MessageAttribute;
import de.javawi.jstun.attribute.MessageAttributeParsingException;
import de.javawi.jstun.header.MessageHeader;
import de.javawi.jstun.util.UtilityException;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UDPServer {
    private DatagramSocket socket;
    private List<String> listQuotes = new ArrayList<>();
    private Random random;

    public UDPServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
        random = new Random();
    }

    public static void main(String[] args) {
        try {
            UDPServer server = new UDPServer(11111); // сажаем сервер на порт 11111
            server.myIPAndPort();
            server.loadQuotesFromFile("Quotes.txt");
            server.service();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UtilityException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Этим методом узнаём айпи и порт за NAT нашего сокета, который сидит на порту 11111
    public void myIPAndPort() throws IOException, UtilityException {
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

    public void service() throws IOException, ClassNotFoundException {
        while (true) {
            // ждём запрос от клиента
            DatagramPacket request = new DatagramPacket(new byte[1024], 1024, InetAddress.getByName("194.39.99.4"), 3084);
            DatagramPacket request2 = new DatagramPacket(new byte[1024], 1024, InetAddress.getByName("194.39.99.4"), 3076);
            socket.send(request);
            socket.send(request2);
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
            socket.receive(response);
            // принимаем и выводим инфо, которая была в запросе от клиента
            byte[] dataFromClient = response.getData();
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(dataFromClient));
            Test test2 = (Test) objectInputStream.readObject();
            System.out.println("Info from client is - " + test2);

            // берем рандомную цитату
            String quote = getRandomQuote();
            byte[] buffer = quote.getBytes();

            // выясняем с какого адреса был послан запрос
            InetAddress clientAddress = request.getAddress();
            InetAddress clientAddress2 = request2.getAddress();
            int clientPort = request.getPort();
            int clientPort2 = request2.getPort();

            // формируем и отправляем ответ клиенту
            DatagramPacket response1 = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
            DatagramPacket response2 = new DatagramPacket(buffer, buffer.length, clientAddress2, clientPort2);
            socket.send(response1);
            socket.send(response2);
        }
    }

    public void loadQuotesFromFile(String quoteFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(quoteFile));
        String aQuote;

        while ((aQuote = reader.readLine()) != null) {
            listQuotes.add(aQuote);
        }
        reader.close();
    }

    private String getRandomQuote() {
        int randomIndex = random.nextInt(listQuotes.size());
        String randomQuote = listQuotes.get(randomIndex);
        return randomQuote;
    }
}
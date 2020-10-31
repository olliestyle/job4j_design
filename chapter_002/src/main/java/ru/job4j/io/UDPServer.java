package ru.job4j.io;

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
            // сажаем сервер на порт 11111
            UDPServer server = new UDPServer(11111);
            server.loadQuotesFromFile("Quotes.txt");
            server.service();
        } catch (SocketException ex) {
            System.out.println("Socket error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void service() throws IOException, ClassNotFoundException {
        while (true) {
            // ждём запрос от клиента
            DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
            socket.receive(request);
            // принимаем и выводим инфо, которая была в запросе от клиента
            byte[] dataFromClient = request.getData();
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(dataFromClient));
            Test test2 = (Test) objectInputStream.readObject();
            System.out.println("Info from client is - " + test2);

            // берем рандомную цитату
            String quote = getRandomQuote();
            byte[] buffer = quote.getBytes();

            // выясняем с какого адреса был послан запрос
            InetAddress clientAddress = request.getAddress();
            int clientPort = request.getPort();

            // формируем и отправляем ответ клиенту
            DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
            socket.send(response);
        }
    }

    private void loadQuotesFromFile(String quoteFile) throws IOException {
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

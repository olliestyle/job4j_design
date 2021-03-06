package ru.job4j.io;

import java.io.*;
import java.util.*;

public class ConsoleChat {
    private final String logPath;
//    private final String botAnswers;
    private final InputStream botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private List<String> answers = new ArrayList<>();

    public ConsoleChat(String logPath, InputStream botAnswers) {
        this.logPath = logPath;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean isBotAnswer = true;
        answersToList(botAnswers);
        StringJoiner toLog = new StringJoiner("\n");
        String answerContinue = "Теперь бот будет отвечать Вам. Введите \"стоп\", чтобы бот перестал с Вами общаться";
        String answerContinueSecond = "Бот не переставал общаться с Вами. Введите \"стоп\", чтобы бот перестал с Вами общаться";
        String answerStop = "Теперь бот будет молчать. Введите \"продолжить\", чтобы бот с Вами продолжил общаться";
        String answerStopSecond = "Бот уже не ведёт с Вами беседу. Введите \"продолжить\", чтобы бот с Вами продолжил общаться";
        System.out.println("Добро пожаловать к нашему боту. Вам доступен следующий функционал:\n"
                + "1. Общение с ботом\n"
                + "2. При вводе слова «стоп», бот прекратит Вам отвечать, но Вы сможете и дальше писать сообщения\n"
                + "3. При вводе слова «продолжить», бот снова начнёт отвечать.\n"
                + "4. При вводе слова «закончить» программа прекращает работу.\n"
                + "Начнём? Введите сообщение");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        toLog.add("Пользователь:\t" + input);
        while (!input.equals(OUT)) {
                switch (input) {
                    case (CONTINUE):
                        if (isBotAnswer) {
                            System.out.println(answerContinueSecond);
                            toLog.add("   Программа:\t" + answerContinueSecond);
                            input = scanner.nextLine();
                            toLog.add("Пользователь:\t" + input);
                        } else {
                            isBotAnswer = true;
                            System.out.println(answerContinue);
                            toLog.add("   Программа:\t" + answerContinue);
                            input = scanner.nextLine();
                            toLog.add("Пользователь:\t" + input);
                        }
                        break;
                    case (STOP):
                        if (!isBotAnswer) {
                            System.out.println(answerStopSecond);
                            toLog.add("   Программа:\t" + answerStopSecond);
                            input = scanner.nextLine();
                            toLog.add("Пользователь:\t" + input);
                        } else {
                            isBotAnswer = false;
                            System.out.println(answerStop);
                            toLog.add("   Программа:\t" + answerStop);
                            input = scanner.nextLine();
                            toLog.add("Пользователь:\t" + input);
                        }
                        break;
                    default:
                        if (isBotAnswer) {
                            String answerBot = getAnswer();
                            System.out.println(answerBot);
                            toLog.add("         Бот:\t" + answerBot);
                            System.out.println("Введите следующее сообщение");
                            input = scanner.nextLine();
                            toLog.add("Пользователь:\t" + input);
                        } else {
                            input = scanner.nextLine();
                            toLog.add("Пользователь:\t" + input);
                        }
                        break;
                }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logPath))) {
                writer.write(toLog.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void answersToList(InputStream botAnswers) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(botAnswers))) {
            String answer;
            while ((answer = reader.readLine()) != null) {
                answers.add(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAnswer() {
        String answerToReturn = "I don't know!";
            if (!answers.isEmpty()) {
                answerToReturn = answers.get(new Random().nextInt(answers.size()));
            }
        return answerToReturn;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("logBot.txt", ConsoleChat.class.getClassLoader().getResourceAsStream("botAnswers.txt"));
        cc.run();
    }
}

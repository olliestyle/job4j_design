package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analyse {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)));
            boolean isOn = true;
            List<String> fromSource = new ArrayList<>();
            in.lines().filter(s -> !s.isEmpty() && !s.startsWith("#")).forEach(fromSource::add);
            for (String s: fromSource) {
                String[] fromS = s.split(" ");
                if (isOn) {
                    if (fromS[0].equals("400") || fromS[0].equals("500")) {
                        isOn = false;
                        pw.write(fromS[1] + ";");
                    }
                } else if (fromS[0].equals("200") || fromS[0].equals("300")) {
                        isOn = true;
                    pw.write(fromS[1] + "\n");
                }
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

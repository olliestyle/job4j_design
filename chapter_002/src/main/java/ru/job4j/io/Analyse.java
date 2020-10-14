package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analyse {
    public void unavailable(String source, String target) {
        List<String> toTarget = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            boolean isOn = true;
            String s;
            while ((s = in.readLine()) != null) {
                String[] fromS = s.split(" ");
                if (isOn) {
                    if (fromS[0].equals("400") || fromS[0].equals("500")) {
                        isOn = false;
                        toTarget.add(fromS[1] + ";");
                    }
                } else if (fromS[0].equals("200") || fromS[0].equals("300")) {
                    isOn = true;
                    toTarget.add(fromS[1] + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (String s: toTarget) {
                pw.write(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

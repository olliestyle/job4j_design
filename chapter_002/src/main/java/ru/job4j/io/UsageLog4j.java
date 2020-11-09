package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean a = true;
        byte b = 127;
        short c = 32767;
        char d = 'd';
        int e = 123123123;
        float f = 372199f;
        long g = 1231225381L;
        double h = 827182918D;

        LOG.debug("primitive types :\n"
                + " boolean a = {}\n"
                + " byte b = {}\n"
                + " short c = {}\n"
                + " char d = {}\n"
                + " int e = {}\n"
                + " float f = {}\n"
                + " long g = {}\n"
                + " double h = {}\n", a, b, c, d, e, f, g, h);
    }
}

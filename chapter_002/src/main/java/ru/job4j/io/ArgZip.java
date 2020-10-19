package ru.job4j.io;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ArgZip {

    private final String[] args;
    private final String directory;
    private final String exclude;
    private final String output;

    public ArgZip(String[] args) {
        this.args = args;
        valid();
        directory = directory();
        exclude = exclude();
        output = output();
    }

    public boolean valid() {
        if (args.length != 3) {
            throw new IllegalArgumentException("Not enough arguments to parse. Arguments must match pattern\n "
                    + "\"-d=directory_to_archive\" "
                    + "\"-e=extension_to_exclude\" "
                    + "\"-o=output_file\"");
        }
        return true;
    }

    private String directory() {
        if (!args[0].contains("=")) {
            throw new IllegalArgumentException("Bad directory. Directory must match pattern \"-d=directory_to_archive\"");
        }
        return args[0].substring(args[0].indexOf("=") + 1);
    }

    private String exclude() {
        if (!args[1].contains("=")) {
            throw new IllegalArgumentException("Bad exclude. Extension to exclude must match pattern \"-e=extension_to_exclude\"");
        }
        return args[1].substring(args[1].indexOf("=") + 1);
    }

    private String output() {
        if (!args[2].contains("=")) {
            throw new IllegalArgumentException("Bad output. Output must match pattern \"-o=output_file\"");
        }
        return args[2].substring(args[2].indexOf("=") + 1);
    }

    public String getDirectory() {
        return directory;
    }

    public String getExclude() {
        return exclude;
    }

    public String getOutput() {
        return output;
    }
}

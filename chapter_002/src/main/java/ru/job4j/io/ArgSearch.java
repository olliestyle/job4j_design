package ru.job4j.io;

public class ArgSearch {
    private final String[] args;
    private final String directory;
    private final String toSearch;
    private final String choice;
    private final String output;

    public ArgSearch(String[] args) {
        this.args = args;
        valid();
        directory = directory();
        toSearch = toSearch();
        choice = choice();
        output = output();
    }

    private boolean valid() {
        if (args.length != 4) {
            throw new IllegalArgumentException("Not enough arguments to parse. Arguments must match pattern\n "
                    + "\"-d=directory_where_search\" "
                    + "\"-n=file_or_mask_or_regex_to_search\" "
                    + "\"-m=search_by_mask(use '*' to replace literals in filename(exmaple: *llo.t* will find hello.txt))\" "
                    + "\"-f=search_by_fullname\" "
                    + "\"-r=search_by_regex\" "
                    + "\"-o=output_file\"");
        }
        return true;
    }

    private String directory() {
        if (!args[0].contains("=")) {
            throw new IllegalArgumentException("Bad directory. Directory must match pattern \"-d=directory_where_search\"");
        }
        return args[0].substring(args[0].indexOf("=") + 1);
    }

    private String toSearch() {
        if (!args[1].contains("=")) {
            throw new IllegalArgumentException("Bad name to search. Name must match pattern \"-n=file_or_mask_or_regex_to_search\"");
        }
        return args[1].substring(args[1].indexOf("=") + 1);
    }

    private String choice() {
        if (!args[2].contains("-m=true") && !args[2].contains("-f=true") && !args[2].contains("-r=true")) {
            throw new IllegalArgumentException("Bad choice. Choice must match pattern \"-m=true OR -f=true OR -r=true\"");
        }
        return String.valueOf(args[2].charAt(1));
    }

    private String output() {
        if (!args[3].contains("=")) {
            throw new IllegalArgumentException("Bad output. Output must match pattern \"-o=output_file\"");
        }
        return args[3].substring(args[3].indexOf("=") + 1);
    }

    public String getDirectory() {
        return directory;
    }

    public String getToSearch() {
        return toSearch;
    }

    public String getChoice() {
        return choice;
    }

    public String getOutput() {
        return output;
    }
}

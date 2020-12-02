package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 1. Создать программу для поиска файла.
 * 2. Программа должна искать данные в заданном каталоге и подкаталогах.
 * 3. Имя файла может задаваться, целиком, по маске, по регулярному выражение(не обязательно).
 * 4. Программа должна собираться в jar и запускаться через java -jar find.jar -d c:/ -n *.txt -m -o log.txt
 * Ключи
 * -d - директория в которой начинать поиск.
 * -n - имя файл, маска, либо регулярное выражение.
 * -m - искать по макс, либо -f - полное совпадение имени. -r регулярное выражение.
 * -o - результат записать в файл.
 * 5. Программа должна записывать результат в файл.
 * 6. В программе должна быть валидация ключей и подсказка.
 */
public class SearchFile {

    public static void main(String[] args) throws IOException {
        ArgSearch argSearch = new ArgSearch(args);
        SearchFile searchFile = new SearchFile();
        List<File> fileList = searchFile.getFileList(argSearch);
        searchFile.writeOutput(fileList, new File(argSearch.getOutput()));
    }

    private void writeOutput(List<File> fileList, File file) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            for (File fileFrom: fileList) {
                pw.write(fileFrom.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SearchFiles setPredicate(String choice, String getToSearch) {
        SearchFiles searchFiles = null;
        switch (choice) {
            case("f"):
                searchFiles = new SearchFiles(p -> p.toFile().getName().equals(getToSearch));
                break;
            case("m"):
                String maskName = getToSearch.substring(0, getToSearch.indexOf(".")).replaceAll("\\*", "");
                String maskExt = getToSearch.substring(getToSearch.indexOf(".") + 1).replaceAll("\\*", "");
                searchFiles = new SearchFiles(p -> p.toFile().getName().contains(maskName) && p.toFile().getName().contains(maskExt));
                break;
            case("r"):
                Pattern pattern = Pattern.compile(getToSearch);
                searchFiles = new SearchFiles(p -> pattern.matcher(p.toFile().getName()).matches());
                break;
            default:
                break;
        }
        return searchFiles;
    }

    private List<File> getFileList(ArgSearch argSearch) throws IOException {
        Path root = Paths.get(argSearch.getDirectory());
        SearchFiles searchFiles = setPredicate(argSearch.getChoice(), argSearch.getToSearch());
        Files.walkFileTree(root, searchFiles);
        List<Path> pathList = searchFiles.getPaths();
        List<File> fileList = new ArrayList<>();
        for (Path path: pathList) {
            fileList.add(path.toFile());
        }
        return fileList;
    }
}

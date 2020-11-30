package ru.job4j.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (File fileFrom: fileList) {
            stringJoiner.add(fileFrom.toString());
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(stringJoiner.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<File> getFileList(ArgSearch argSearch) throws IOException {
        Path root = Paths.get(argSearch.getDirectory());
        SearchFiles searchFiles = null;
        String toSearch = argSearch.getToSearch();
        switch (argSearch.getChoice()) {
            case("f"):
                searchFiles = new SearchFiles(p -> p.toFile().getName().equals(toSearch));
                break;
            case("m"):
                String maskName = toSearch.substring(0, toSearch.indexOf(".")).replaceAll("\\*", "");
                String maskExt = toSearch.substring(toSearch.indexOf(".") + 1).replaceAll("\\*", "");
                searchFiles = new SearchFiles(p -> p.toFile().getName().contains(maskName) && p.toFile().getName().contains(maskExt));
                break;
            case("r"):
                String reg = argSearch.getToSearch();
                searchFiles = new SearchFiles(p -> p.toFile().getName().matches(reg));
                break;
            default:
                break;
        }
        Files.walkFileTree(root, searchFiles);
        List<Path> pathList = searchFiles.getPaths();
        List<File> fileList = new ArrayList<>();
        for (Path path: pathList) {
            fileList.add(path.toFile());
        }
        return fileList;
    }
}

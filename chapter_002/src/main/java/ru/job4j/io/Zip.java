package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file: sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 1. В zip создаем новую ZipEntry(что-то типа отдельного файла в архиве) и в неё записываем из source все байты.
     * @param source
     * @param target
     */
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<File> getFileList(ArgZip args) throws IOException {
        Path root = Paths.get(args.getDirectory());
        SearchFiles searchFiles = new SearchFiles(p -> !p.toFile().getName().endsWith(args.getExclude()));
        Files.walkFileTree(root, searchFiles);
        List<Path> pathList = searchFiles.getPaths();
        List<File> fileList = new ArrayList<>();
        for (Path path: pathList) {
            fileList.add(path.toFile());
        }
        return fileList;
    }

    public static void main(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);
        new Zip().packFiles(getFileList(argZip), new File(argZip.getOutput()));
    }
}

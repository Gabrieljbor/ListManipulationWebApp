package fileio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

public class FileManipulation {
    public static void deleteDir(File dir) {
        if (dir.isDirectory()) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                deleteDir(file);
            }
        }
        dir.delete();
    }

    public static void makeFile(String filePath, String fileName, String text) throws IOException {
        String path = filePath + File.separator + fileName;
        File file = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(text);
        }
    }
    //test

    public static String readFileContent(String path) {
        Path filePath = Paths.get(path);
        try {
            Optional<String> content = Files.lines(filePath)
                    .findFirst();
            return content.orElse("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package fileio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class GetFileData {

    private static final String dataDirPath = "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "data" + File.separator;

    public static String[] getListNamesFromData() throws IOException {
        Path dataDir = Paths.get(dataDirPath);
        try (Stream<Path> stream = Files.list(dataDir)) {
            return stream
                    .filter(Files::isDirectory)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .sorted()
                    .toArray(String[]::new);
        }
    }

    public static String[] getItemNamesFromData(String listName) {

        Path dataDir = Paths.get(dataDirPath + File.separator + listName);
        try (Stream<Path> stream = Files.list(dataDir)) {
            return stream
                    .filter(Files::isDirectory)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .sorted()
                    .toArray(String[]::new);
        } catch (Exception e) {
            return new String[0];
        }
    }

    public static String getItemTextFromData(String listName, String itemName) {
        String path = dataDirPath + File.separator + listName + File.separator + itemName;
        File file = new File(path + File.separator + "text.txt");

        if (!file.exists()) {
            return "";
        }

        return FileHelpers.readFileContent(file.getPath());
    }

    public static String getItemURLFromData(String listName, String itemName) {
        String path = dataDirPath + listName + File.separator + itemName;
        File file = new File(path, "url.txt");

        if (!file.exists()) {
            return "";
        }

        return FileHelpers.readFileContent(file.getPath());
    }

    public static String getItemImageFromData(String listName, String itemName) {
        String imgPath = dataDirPath + listName + File.separator + itemName + File.separator + "img.jpg";
        File img = new File(imgPath);
        if (!img.exists()) {
            return "";
        }
        return "data" + File.separator + listName + File.separator + itemName + File.separator + "img.jpg";
    }

    public static String getItemListLinkFromData(String listName, String itemName) {
        String path = dataDirPath + listName + File.separator + itemName;
        File file = new File(path, "listLink.txt");

        if (!file.exists()) {
            return "";
        }
        return FileHelpers.readFileContent(file.getPath());
    }
}

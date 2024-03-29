package item;

import fileio.FileHelpers;

import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class ItemFilesEditor {

    //updates the Item's files in the data directory
    private static final String dataDirPath = "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "data" + File.separator;

    public static void changeItemNameInData(Item item, String newName) {
        Path oldPath = Paths.get(dataDirPath, item.getList(), item.getName());
        Path newPath = Paths.get(dataDirPath, item.getList(), newName);
        try {
            Files.move(oldPath, newPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setItemTextInData(Item item, String text) throws IOException {
        FileHelpers.makeFile(dataDirPath + item.getList() + File.separator + item.getName(), "text.txt", text);
    }

    public static void setItemURLInData(Item item, String URL) throws IOException {
        FileHelpers.makeFile(dataDirPath + item.getList() + File.separator + item.getName(), "url.txt", URL);
    }

    public static void setItemImageInData(Item item, Part filePart) throws IOException {
        File itemDir = new File(dataDirPath, item.getList() + File.separator + item.getName());

        try (OutputStream out = new FileOutputStream(new File(itemDir, "img.jpg"));
             InputStream fileContent = filePart.getInputStream()) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }

    public static void deleteItemImageInData(Item item) {
        FileHelpers.deleteDir(new File(dataDirPath + item.getList() + File.separator + item.getName() + File.separator + "img.jpg"));
    }

    public static void setItemListLinkInData(Item item, String listLink) throws IOException {
        FileHelpers.makeFile(dataDirPath + item.getList() + File.separator + item.getName(), "listLink.txt", listLink);
    }
}

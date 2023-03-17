package item;

import fileio.FileManipulation;
import fileio.GetFileData;

import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Item {
    private static final String dataDirPath = "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "data" + File.separator;

    public String list, name, text, url, image, listLink;

    public Item(String listName, String itemName) throws IOException {
        this.list = listName;
        this.name = itemName;
        this.text = GetFileData.getItemText(listName, itemName);
        this.url = GetFileData.getItemURL(listName, itemName);
        this.image = GetFileData.getItemImage(listName, itemName);
        this.listLink = GetFileData.getItemListLink(listName, itemName);
    }


    public boolean changeItemName(String newName) {
        String oldName = this.name;
        this.name = newName;
        Path oldPath = Paths.get(dataDirPath, this.list, oldName);
        Path newPath = Paths.get(dataDirPath, this.list, newName);

        try {
            Files.move(oldPath, newPath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Set item text
    public void setItemText(String text) throws IOException {
        this.text = text;
        FileManipulation.makeFile(dataDirPath + this.list + File.separator + this.name, "text.txt", text);
    }


    // Set and get item URL
    public void setItemURL(String URL) throws IOException {
        this.url = URL;
        FileManipulation.makeFile(dataDirPath + this.list + File.separator + this.name, "url.txt", URL);
    }


    // Set and get item Image
    public boolean setItemImage(Part filePart) throws IOException {
        File itemDir = new File(dataDirPath, this.list + File.separator + this.name);

        try (OutputStream out = new FileOutputStream(new File(itemDir, "img.jpg"));
             InputStream fileContent = filePart.getInputStream()) {

            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            this.image = "data" + File.separator + this.list + File.separator + this.name + File.separator + "img.jpg";
            return true;
        }
    }

    public void deleteItemImage() {
        this.image = "";
        FileManipulation.deleteDir(new File(dataDirPath + this.list + File.separator + this.name + File.separator + "img.jpg"));
    }

    public void setItemListLink(String listLink) throws IOException {
        this.listLink = listLink;
        FileManipulation.makeFile(dataDirPath + this.list + File.separator + this.name, "listLink.txt", listLink);
    }

}

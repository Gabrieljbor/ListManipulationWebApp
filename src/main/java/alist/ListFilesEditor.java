package alist;

import fileio.FileHelpers;
import item.Item;
import model.ModelFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ListFilesEditor {
    private static final String dataDirPath = "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "data" + File.separator;

    public static void addListInData(String listName) {
        Path listPath = Paths.get(dataDirPath, listName);
        if (Files.exists(listPath)) {
            return;
        }
        try {
            Files.createDirectories(listPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void deleteListInData(String listName) {
        try {
            FileHelpers.deleteDir(new File(dataDirPath + listName));
            updateLinkList(listName, listName);
        } catch (IOException ignored) {
        }
    }

    public static void editListNameInData(String listName, String newListName) throws IOException {
        if (listName.equalsIgnoreCase(newListName)) {
            return;
        }
        File oldDir = new File(dataDirPath + listName);
        File newDir = new File(dataDirPath + newListName);
        if (oldDir.renameTo(newDir)) {
            updateLinkList(listName, newListName);
        }
    }

    private static void updateLinkList(String changedListName, String newPath) throws IOException {
        //Changes all occurrences of the list that is changed
        for (AList list : ModelFactory.getModel().allLists) {
            for (Item item : list.getItems()) {
                String itemLink = item.getListLink();
                if (changedListName.equals(itemLink)) {
                    item.setListLink(changedListName.equals(newPath) ? "" : newPath);
                }
            }
        }
    }

    public static void addListItemInData(String listName, String itemName) {
        Path itemPath = Paths.get(dataDirPath, listName + File.separator + itemName);
        if (Files.exists(itemPath)) {
            return;
        }
        try {
            Files.createDirectories(itemPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteListItem(String listName, String itemName){
        String path = dataDirPath + listName + File.separator + itemName;
        FileHelpers.deleteDir(new File(path));
    }
}

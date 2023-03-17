package alist;

import fileio.GetFileData;
import fileio.FileManipulation;
import item.Item;
import model.Model;
import model.ModelFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

public class ListFilesEditor {
    private static final String dataDirPath = "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "data" + File.separator;

    public static boolean addList(String listName) {
        Path listPath = Paths.get(dataDirPath, listName);
        if (Files.exists(listPath)) {
            return false;
        }

        try {
            Files.createDirectories(listPath);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean deleteList(String listName) {
        try {
            FileManipulation.deleteDir(new File(dataDirPath + listName));
            updateLinkList(listName, listName);
            return true;
        } catch (IOException e){
            return false;
        }
    }

    private static void updateLinkList(String changedListName, String newPath) throws IOException {
        for (AList list : ModelFactory.getModel().allLists) {
            for (Item item : list.items) {
                String itemLink = item.listLink;
                if (changedListName.equals(itemLink)) {
                    item.listLink = changedListName.equals(newPath) ? "" : newPath;
                }
            }
        }
    }

    public static boolean editListName(String listName, String newListName) throws IOException {
        if (listName.equalsIgnoreCase(newListName)) {
            return true;
        }
        File oldDir = new File(dataDirPath + listName);
        File newDir = new File(dataDirPath + newListName);
        if (oldDir.renameTo(newDir)) {
            updateLinkList(listName, newListName);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addListItem(String listName, String itemName) {
        Path itemPath = Paths.get(dataDirPath, listName + File.separator + itemName);
        if (Files.exists(itemPath)) {
            return false;
        }

        try {
            Files.createDirectories(itemPath);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] getListItems(String listName){
        File list = new File(dataDirPath + listName);
        return list.isDirectory() ? Arrays.stream(Objects.requireNonNull(list.list()))
                .sorted()
                .toArray(String[]::new) : new String[0];
    }

    public static void deleteListItem(String listName, String itemName){
        String path = dataDirPath + listName + File.separator + itemName;
        FileManipulation.deleteDir(new File(path));
    }
}

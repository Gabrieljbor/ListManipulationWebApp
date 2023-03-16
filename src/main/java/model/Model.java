package model;

import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Model
{



  // Path to the data directory
  private final String dataDirPath = "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "data" + File.separator;

  // Manage the lists (add, get, delete, edit):
  // Add a new list directory in the data directory
  public boolean addList(String listName) {
    if (listName == null || listName.isEmpty()) {
      return false;
    }

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

  // Get the names of all the lists
  public String[] getListNames() throws IOException {
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


  // Delete list directory
  public boolean deleteList(String listName) {
    try {
      deleteDir(new File(dataDirPath + listName));
      updateLinkToList(listName, listName);
      return true;
    } catch (IOException e){
      return false;
    }
  }

  // Edit the name of the list
  public boolean editListName(String listName, String newListName) throws IOException {
    if (listName.equalsIgnoreCase(newListName)) {
      return true;
    }
    File oldDir = new File(dataDirPath + listName);
    File newDir = new File(dataDirPath + newListName);
    if (oldDir.renameTo(newDir)) {
      updateLinkToList(listName, newListName);
      return true;
    } else {
      return false;
    }
  }


  // Manage the list items (add, get, delete, edit):
  // Add items to a list
  public boolean addListItem(String listName, String itemName) {
    String path = dataDirPath + listName + File.separator + itemName;

    try {
      File itemDir = new File(path);
      if (itemDir.mkdirs()) {
        setItemText(listName, itemName, "");
        setItemURL(listName, itemName, "");
        setItemListLink(listName, itemName, "");
        return true;
      } else {
        return false;
      }
    } catch (IOException e){
      return false;
    }
  }

  // Get items from a list
  public String[] getListItems(String listName){
    File list = new File(dataDirPath + listName);
    return list.isDirectory() ? Arrays.stream(Objects.requireNonNull(list.list()))
            .sorted()
            .toArray(String[]::new) : new String[0];
  }

  // Delete item from a list
  public void deleteListItem(String listName, String itemName){
    String path = dataDirPath + listName + File.separator + itemName;
    deleteDir(new File(path));
  }

  // Editing the items in the list (set, get) :
  // Edit item's name
  public boolean changeItemName(String listName, String itemName, String newName) {
    Path oldPath = Paths.get(dataDirPath, listName, itemName);
    Path newPath = Paths.get(dataDirPath, listName, newName);

    try {
      Files.move(oldPath, newPath);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  // Set and get item text
  public void setItemText(String listName, String itemName, String text) throws IOException {
    makeFile(dataDirPath + listName + File.separator + itemName, "text.txt", text);
  }

  public String getItemText(String listName, String itemName) throws IOException {
    String path = dataDirPath + File.separator + listName + File.separator + itemName;
    File file = new File(path + File.separator + "text.txt");

    if (!file.exists()) {
      setItemText(listName, itemName, "");
    }

    return readFileContent(file.getPath());
  }


  // Set and get item URL
  public void setItemURL(String listName, String itemName, String URL) throws IOException {
    makeFile(dataDirPath + listName + File.separator + itemName, "url.txt", URL);
  }

  public String getItemURL(String listName, String itemName) throws IOException {
    String path = dataDirPath + listName + File.separator + itemName;
    File file = new File(path, "url.txt");

    if (!file.exists()) {
      setItemURL(listName, itemName, "");
    }

    return readFileContent(file.getPath());
  }

  // Set and get item Image
  public boolean setItemImage(String listName, String itemName, Part filePart) throws IOException {
    File itemDir = new File(dataDirPath, listName + File.separator + itemName);

    try (OutputStream out = new FileOutputStream(new File(itemDir, "img.jpg"));
         InputStream fileContent = filePart.getInputStream()) {

      byte[] buffer = new byte[8192];
      int bytesRead;
      while ((bytesRead = fileContent.read(buffer)) != -1) {
        out.write(buffer, 0, bytesRead);
      }
      return true;
    }
  }

  public String[] getItemImage(String listName, String itemName) {
    String imgPath = dataDirPath + listName + File.separator + itemName + File.separator + "img.jpg";
    File img = new File(imgPath);
    if (!img.exists()) {
      return new String[]{"",""};
    }
    String fileName = img.getName();
    return new String[]{fileName, "data" + File.separator + listName + File.separator + itemName + File.separator + "img.jpg"};
  }

  public void deleteItemImage(String listName, String itemName) {
    deleteDir(new File(dataDirPath + listName + File.separator + itemName + File.separator + "img.jpg"));
  }


  // Set, get and update links to other list
  public void setItemListLink(String listName, String itemName, String listToLink) throws IOException {
    makeFile(dataDirPath + listName + File.separator + itemName, "listLink.txt", listToLink);
  }

  public String getItemListLink(String listName, String itemName) throws IOException {
    String path = dataDirPath + listName + File.separator + itemName;
    File file = new File(path, "listLink.txt");

    if (!file.exists()) {
      setItemListLink(listName, itemName, "");
    }

    return readFileContent(file.getPath());
  }

  private void updateLinkToList(String changedListName, String newPath) throws IOException {
    for (String listName : getListNames()) {
      for (String itemName : getListItems(listName)) {
        String itemLink = getItemListLink(listName, itemName);
        if (changedListName.equals(itemLink)) {
          setItemListLink(listName, itemName, changedListName.equals(newPath) ? "" : newPath);
        }
      }
    }
  }

  // Helper methods:
  private void deleteDir(File dir) {
    if (dir.isDirectory()) {
      for (File file : Objects.requireNonNull(dir.listFiles())) {
        deleteDir(file);
      }
    }
    dir.delete();
  }

  private void makeFile(String filePath, String fileName, String text) throws IOException {
    String path = filePath + File.separator + fileName;
    File file = new File(path);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write(text);
    }
  }
  //test

  private String readFileContent(String path) {
    Path filePath = Paths.get(path);
    try {
      Optional<String> content = Files.lines(filePath)
              .findFirst();
      return content.orElse("");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  // Get all data into a more usable data structure
  public Map<String, Map<String, String[]>> getAllData() throws IOException {
    Map<String, Map<String, String[]>> allData = new TreeMap<>();

    for (String listName : getListNames()) {
      Map<String, String[]> listData = new TreeMap<>();

      for (String itemName : getListItems(listName)) {
        String[] itemData = new String[5];
        itemData[0] = getItemText(listName, itemName);
        itemData[1] = getItemURL(listName, itemName);
        itemData[2] = getItemImage(listName, itemName)[0];
        itemData[3] = getItemImage(listName, itemName)[1];
        itemData[4] = getItemListLink(listName, itemName);
        listData.put(itemName, itemData);
      }
      allData.put(listName, listData);
    }
    return allData;
  }


  public Map<String, List<String>> searchForItem(String searchQuery) throws IOException {
    if (getListNames() == null || getListNames().length == 0) {
      return Collections.emptyMap();
    }
    Map<String, List<String>> searchResults = new TreeMap<>();
    for (String list : getListNames()) {
      String[] items = getListItems(list);
      ArrayList<String> matchingItems = new ArrayList<>();
      for (String item : items) {
        if (item.toLowerCase().contains(searchQuery.toLowerCase())) {
          matchingItems.add(item);
        }
      }
      searchResults.put(list, matchingItems.stream().toList());
    }
    return searchResults;
  }

  public List<String> searchForList(String searchQuery) throws IOException {
    if (getListNames() == null || getListNames().length == 0) {
      return Collections.emptyList();
    }
    List<String> matchingLists = new ArrayList<>();
    for (String list : getListNames()) {
      if (list.toLowerCase().contains(searchQuery.toLowerCase())) {
        matchingLists.add(list);
      }
    }
    return matchingLists;
  }

}

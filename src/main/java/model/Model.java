package model;

import alist.AList;
import alist.ListFilesEditor;
import fileio.GetFileData;
import item.Item;
import item.ItemFilesEditor;
import javax.servlet.http.Part;
import java.io.*;
import java.util.*;

public class Model
{
  //ArrayList of all lists of items
  private final ArrayList<AList> allLists = new ArrayList<>();

  public Model() throws IOException {
    String[] listNames = GetFileData.getListNamesFromData();
    for (String listName : listNames) {
      allLists.add(new AList(listName));
    }
  }

  public ArrayList<AList> getAllLists() {
    return allLists;
  }

  //Gets list object from the name of a list
  public AList getList(String listName) {
    for (AList list : allLists) {
      if (list.getName().equals(listName)) {
        return list;
      }
    }
    return null;
  }

  // Manage the lists (add, get, delete, edit):
  public void addList(String listName) throws IOException {
    if (listName == null || listName.isEmpty()) {
      return;
    }
    for (AList list : allLists) {
      if (listName.equals(list.getName())) {
        return;
      }
    }
    allLists.add(new AList(listName));
    allLists.sort(Comparator.comparing(AList::getName));
    ListFilesEditor.addListInData(listName);
  }
  public String[] getListNames() {
    ArrayList<String> listNames = new ArrayList<>();
    for (AList list : allLists) {
      listNames.add(list.getName());
    }
    String[] names = new String[listNames.size()];
    return listNames.toArray(names);
  }
  public void deleteList(String listName) {
    this.allLists.remove(getList(listName));
    ListFilesEditor.deleteListInData(listName);
  }
  public void changeListName(String listName, String newListName) throws IOException {
    for (AList list : allLists) {
      if (newListName.equals(list.getName())) {
        return;
      }
      for (Item item : list.getItems()) {
          if (item.getListLink().equals(listName)) {
            ItemFilesEditor.setItemListLinkInData(item, newListName);
          }
      }
    }
    AList list = getList(listName);
    list.setName(newListName);
    for (Item item : list.getItems()) {
      item.setList(newListName);
      if (!item.getImage().equals("")) {
        item.setImage("data" + File.separator + newListName + File.separator + item.getName() + File.separator + "img.jpg");
      }
    }

    ListFilesEditor.editListNameInData(listName, newListName);
  }

  // Manage the list items (add, get, delete, edit):
  public void addListItem(String listName, String itemName) throws IOException {
    AList list = getList(listName);
    list.addListItem(itemName);
    ListFilesEditor.addListItemInData(listName, itemName);
  }
  public String[] getListItems(String listName) {
    ArrayList<String> itemNames = new ArrayList<>();
    for (Item item : getList(listName).getItems()) {
      itemNames.add(item.getName());
    }
    String[] names = new String[itemNames.size()];
    return itemNames.toArray(names);
  }
  public void deleteListItem(String listName, String itemName){
    AList list = getList(listName);
    list.deleteItem(list.getItem(itemName));
    ListFilesEditor.deleteListItem(listName, itemName);
  }

  // Editing the items in the list:
  public void changeItemName(String listName, String itemName, String newName) {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    item.changeItemName(newName);
  }
  public void setItemText(String listName, String itemName, String text) throws IOException {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    item.setItemText(text);
  }
  public void setItemURL(String listName, String itemName, String URL) throws IOException {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    item.setItemURL(URL);
  }
  public void setItemImage(String listName, String itemName, Part filePart) throws IOException {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    item.setItemImage(filePart);
  }
  public void deleteItemImage(String listName, String itemName) {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    item.deleteItemImage();
  }
  public void setItemListLink(String listName, String itemName, String listLink) throws IOException {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    item.setItemListLink(listLink);
  }

  //Searching for items and lists:
  public List<Item> searchForItem(String searchQuery) {
    if (getListNames() == null || getListNames().length == 0) {
      return Collections.emptyList();
    }
    List<Item> searchResults = new ArrayList<>();
    for (AList list : allLists) {
      for (Item item : list.getItems()) {
        if (item.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
          searchResults.add(item);
        }
      }
    }
    return searchResults;
  }

  public List<AList> searchForList(String searchQuery) {
    if (getListNames() == null || getListNames().length == 0) {
      return Collections.emptyList();
    }
    List<AList> searchResults = new ArrayList<>();
    for (AList list : allLists) {
      if (list.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
        searchResults.add(list);
      }
    }
    return searchResults;
  }

}

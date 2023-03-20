package model;

import alist.AList;
import alist.ListFilesEditor;
import fileio.GetFileData;
import item.Item;

import javax.servlet.http.Part;
import java.io.*;
import java.util.*;

public class Model
{
  //ArrayList of all lists of items
  public ArrayList<AList> allLists = new ArrayList<>();

  public Model() throws IOException {
    String[] listNames = GetFileData.getListNamesFromData();
    for (String listName : listNames) {
      allLists.add(new AList(listName));
    }
  }

  public AList getList(String listName) {
    for (AList list : allLists) {
      if (list.name.equals(listName)) {
        return list;
      }
    }
    return null;
  }

  // Manage the lists (add, get, delete, edit):
  // Add a new list directory in the data directory
  public void addList(String listName) throws IOException {
    if (listName == null || listName.isEmpty()) {
      return;
    }
    allLists.add(new AList(listName));
    ListFilesEditor.addListInData(listName);
  }
  // Get the names of all the lists
  public String[] getListNames() throws IOException {
    ArrayList<String> listNames = new ArrayList<>();
    for (AList list : allLists) {
      listNames.add(list.name);
    }
    String[] names = new String[listNames.size()];
    return listNames.toArray(names);
  }
  // Delete list directory
  public void deleteList(String listName) {
    this.allLists.remove(getList(listName));
    ListFilesEditor.deleteListInData(listName);
  }
  // Edit the name of the list
  public void editListName(String listName, String newListName) throws IOException {
    AList list = getList(listName);
    list.name = newListName;
    ListFilesEditor.editListNameInData(listName, newListName);
  }
  // Manage the list items (add, get, delete, edit):
  // Add items to a list
  public void addListItem(String listName, String itemName) throws IOException {
    AList list = getList(listName);
    list.addListItem(itemName);
    ListFilesEditor.addListItemInData(listName, itemName);
  }
  // Get items from a list
  public String[] getListItems(String listName) {
    return ListFilesEditor.getListItems(listName);
  }
  // Delete item from a list
  public void deleteListItem(String listName, String itemName){
    AList list = getList(listName);
    list.items.remove(list.getItem(itemName));
    ListFilesEditor.deleteListItem(listName, itemName);
  }

  // Editing the items in the list (set, get) :
  // Edit item's name
  public void changeItemName(String listName, String itemName, String newName) {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    item.changeItemName(newName);
  }
  // Set and get item text
  public void setItemText(String listName, String itemName, String text) throws IOException {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    item.setItemText(text);
  }
  public String getItemText(String listName, String itemName) throws IOException {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    return item.text;
  }
  // Set and get item URL
  public void setItemURL(String listName, String itemName, String URL) throws IOException {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    item.setItemURL(URL);
  }
  public String getItemURL(String listName, String itemName) throws IOException {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    return item.url;
  }
  // Set and get item Image
  public void setItemImage(String listName, String itemName, Part filePart) throws IOException {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    item.setItemImage(filePart);
  }
  public String getItemImage(String listName, String itemName) {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    return item.image;
  }
  public void deleteItemImage(String listName, String itemName) {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    item.deleteItemImage();
  }
  // Set, get and update links to other list
  public void setItemListLink(String listName, String itemName, String listLink) throws IOException {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    item.setItemListLink(listLink);
  }

  public String getItemListLink(String listName, String itemName) throws IOException {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    return item.listLink;
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

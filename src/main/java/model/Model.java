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
    for (AList list : allLists) {
      if (listName.equals(list.name)) {
        return;
      }
    }
    allLists.add(new AList(listName));
    ListFilesEditor.addListInData(listName);
  }
  // Get the names of all the lists
  public String[] getListNames() {
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
  public void changeListName(String listName, String newListName) throws IOException {
    for (AList list : allLists) {
      if (newListName.equals(list.name)) {
        return;
      }
      for (Item item : list.items) {
          if (item.listLink.equals(listName)) {
            ItemFilesEditor.setItemListLinkInData(item, newListName);
          }
      }
    }
    AList list = getList(listName);
    list.name = newListName;
    for (Item item : list.items) {
      item.list = newListName;
      if (!item.image.equals("")) {
        item.image = "data" + File.separator + newListName + File.separator + item.name + File.separator + "img.jpg";
      }
    }

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
    ArrayList<String> itemNames = new ArrayList<>();
    for (Item item : getList(listName).items) {
      itemNames.add(item.name);
    }
    String[] names = new String[itemNames.size()];
    return itemNames.toArray(names);
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
  public String getItemText(String listName, String itemName) {
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
  public String getItemURL(String listName, String itemName) {
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

  public String getItemListLink(String listName, String itemName) {
    AList list = getList(listName);
    Item item = list.getItem(itemName);
    return item.listLink;
  }


  public List<Item> searchForItem(String searchQuery) {
    if (getListNames() == null || getListNames().length == 0) {
      return Collections.emptyList();
    }
    List<Item> searchResults = new ArrayList<>();
    for (AList list : allLists) {
      for (Item item : list.items) {
        if (item.name.toLowerCase().contains(searchQuery.toLowerCase())) {
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
      if (list.name.toLowerCase().contains(searchQuery.toLowerCase())) {
        searchResults.add(list);
      }
    }
    return searchResults;
  }

}

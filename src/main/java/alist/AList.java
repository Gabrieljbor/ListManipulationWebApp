package alist;

import fileio.GetFileData;
import item.Item;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AList {

    //AList is an object that has a list of Items
    private final List<Item> items;
    private String name;

    public AList(String listName) throws IOException {
        this.name = listName;
        String [] itemNames = GetFileData.getItemNamesFromData(this.name);
        ArrayList<Item> items = new ArrayList<>();
        for (String itemName : itemNames) {
            items.add(new Item(listName, itemName));
        }
        this.items = items;
    }

    //Get the Item object from the itemName
    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void deleteItem(Item item) {
        items.remove(item);
    }

    public void addListItem(String itemName) throws IOException {
        if (itemName == null || itemName.isEmpty()) {
            return;
        }
        for (Item item : this.items) {
            if (itemName.equals(item.getName())) {
                return;
            }
        }
        items.add(new Item(this.name, itemName));
        ListFilesEditor.addListItemInData(this.name, itemName);
    }

    public List<Item> getItems() {
        return new ArrayList<>(this.items);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

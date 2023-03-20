package alist;

import fileio.GetFileData;
import item.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AList {

    public List<Item> items;
    public String name;

    public AList(String listName) throws IOException {
        this.name = listName;
        String [] itemNames = GetFileData.getItemNamesFromData(this.name);
        ArrayList<Item> items = new ArrayList<>();
        for (String itemName : itemNames) {
            items.add(new Item(listName, itemName));
        }
        this.items = items;
    }

    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.name.equals(itemName)) {
                return item;
            }
        }
        return null;
    }
    public void addListItem(String itemName) throws IOException {
        if (itemName == null || itemName.isEmpty()) {
            return;
        }
        for (Item item : this.items) {
            if (itemName.equals(item.name)) {
                return;
            }
        }
        items.add(new Item(this.name, itemName));
        ListFilesEditor.addListItemInData(this.name, itemName);
    }


}

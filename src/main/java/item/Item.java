package item;

import fileio.GetFileData;

import javax.servlet.http.Part;
import java.io.*;


public class Item {

    public String list, name, text, url, image, listLink;

    public Item(String listName, String itemName) throws IOException {
        this.list = listName;
        this.name = itemName;
        this.text = GetFileData.getItemTextFromData(listName, itemName);
        this.url = GetFileData.getItemURLFromData(listName, itemName);
        this.image = GetFileData.getItemImageFromData(listName, itemName);
        this.listLink = GetFileData.getItemListLinkFromData(listName, itemName);
    }


    public boolean changeItemName(String newName) {
        ItemFilesEditor.changeItemNameInData(this, newName);
        this.name = newName;
        return true;
    }

    // Set item text
    public void setItemText(String text) throws IOException {
        this.text = text;
        ItemFilesEditor.setItemTextInData(this, text);
    }


    // Set item URL
    public void setItemURL(String URL) throws IOException {
        this.url = URL;
        ItemFilesEditor.setItemURLInData(this, URL);
    }


    // Set item Image
    public boolean setItemImage(Part filePart) throws IOException {
        this.image = "data" + File.separator + this.list + File.separator + this.name + File.separator + "img.jpg";
        ItemFilesEditor.setItemImageInData(this, filePart);
        return true;
    }

    public void deleteItemImage() {
        this.image = "";
        ItemFilesEditor.deleteItemImageInData(this);
    }

    public void setItemListLink(String listLink) throws IOException {
        this.listLink = listLink;
        ItemFilesEditor.setItemListLinkInData(this, listLink);
    }

}

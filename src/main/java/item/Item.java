package item;

import fileio.GetFileData;
import javax.servlet.http.Part;
import java.io.*;


public class Item {
    //This is an item in a list which has a name and could have text, an url, an image or link to another list
    private String list, name, text, url, image, listLink;

    public Item(String listName, String itemName) throws IOException {
        this.list = listName;
        this.name = itemName;
        this.text = GetFileData.getItemTextFromData(listName, itemName);
        this.url = GetFileData.getItemURLFromData(listName, itemName);
        this.image = GetFileData.getItemImageFromData(listName, itemName);
        this.listLink = GetFileData.getItemListLinkFromData(listName, itemName);
    }

    public void changeItemName(String newName) {
        ItemFilesEditor.changeItemNameInData(this, newName);
        this.name = newName;
    }

    public void setItemText(String text) throws IOException {
        this.text = text;
        ItemFilesEditor.setItemTextInData(this, text);
    }

    public void setItemURL(String URL) throws IOException {
        this.url = URL;
        ItemFilesEditor.setItemURLInData(this, URL);
    }

    public void setItemImage(Part filePart) throws IOException {
        this.image = "data" + File.separator + this.list + File.separator + this.name + File.separator + "img.jpg";
        ItemFilesEditor.setItemImageInData(this, filePart);
    }

    public void deleteItemImage() {
        this.image = "";
        ItemFilesEditor.deleteItemImageInData(this);
    }

    public void setItemListLink(String listLink) throws IOException {
        this.listLink = listLink;
        ItemFilesEditor.setItemListLinkInData(this, listLink);
    }

    public String getList() {
        return this.list;
    }

    public String getName() {
        return this.name;
    }

    public String getText() {
        return this.text;
    }

    public String getUrl() {
        return this.url;
    }

    public String getListLink() {
        return this.listLink;
    }

    public String getImage() {
        return this.image;
    }

    public void setList(String newListName) {
        this.list = newListName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setListLink(String listLink) {
        this.listLink = listLink;
    }
}


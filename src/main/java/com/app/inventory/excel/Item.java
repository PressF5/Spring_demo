package com.app.inventory.excel;

public class Item {
    private int number;
    private String description;
    private int countItems;
    private int office;

    public Item(int number, String description, int countItems, int office) {
        this.number = number;
        this.description = description;
        this.countItems = countItems;
        this.office = office;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public int getCountItems() {
        return countItems;
    }

    public int getOffice() {
        return office;
    }
}

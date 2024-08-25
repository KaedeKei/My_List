package com.example.my_list;

public class Item {
    private long id;
    private String name;

    public Item (long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return this.name  + "\n";
    }
}

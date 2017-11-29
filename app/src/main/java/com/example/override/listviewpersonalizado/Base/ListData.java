package com.example.override.listviewpersonalizado.Base;

/**
 * Created by Override on 16/10/2016.
 */
public class ListData {
    String _id;
    String name;
    String description;
    String price;

    public ListData(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ListData() {
    }

    public String get_Id() {
        return _id;
    }

    public void set_Id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ListData{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

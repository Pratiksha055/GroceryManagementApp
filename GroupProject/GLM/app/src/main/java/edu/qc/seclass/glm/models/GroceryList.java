package edu.qc.seclass.glm.models;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grocery_list")
public class GroceryList implements Serializable {

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    int grocery_list_id;

    @ColumnInfo
    String grocery_list_name;


    public GroceryList(String grocery_list_name) {
        this.grocery_list_name = grocery_list_name;
    }

    public int getGroceryListId() {
        return grocery_list_id;
    }

    public void setGroceryListId(int groceryListId) {
        this.grocery_list_id = groceryListId;
    }

    public String getGroceryListName() {
        return grocery_list_name;
    }

    public void setGroceryListName(String groceryListName) {
        this.grocery_list_name = groceryListName;
    }
}

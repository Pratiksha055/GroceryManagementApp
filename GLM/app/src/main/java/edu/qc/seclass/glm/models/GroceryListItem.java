package edu.qc.seclass.glm.models;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "grocery_list_item", foreignKeys = {@ForeignKey(entity = GroceryList.class, parentColumns = "grocery_list_id", childColumns = "grocery_list_id")})
public class GroceryListItem implements Serializable {

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    int grocery_list_item_id;

    @ColumnInfo
    int grocery_list_id;

    @ColumnInfo
    String item_name;

    @ColumnInfo
    int quantity;

    @ColumnInfo
    boolean marked;

    @Embedded(prefix = "grocerylist_")
    GroceryList groceryList;

    public GroceryListItem(String item_name, int grocery_list_id, int quantity, boolean marked) {
        this.grocery_list_id = grocery_list_id;
        this.item_name = item_name;
        this.quantity = quantity;
        this.marked = marked;
    }

    public int getGrocery_list_item_id() {
        return grocery_list_item_id;
    }

    public void setGrocery_list_item_id(int grocery_list_item_id) {
        this.grocery_list_item_id = grocery_list_item_id;
    }

    public int getGrocery_list_id() {
        return grocery_list_id;
    }

    public void setGrocery_list_id(int grocery_list_id) {
        this.grocery_list_id = grocery_list_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
}

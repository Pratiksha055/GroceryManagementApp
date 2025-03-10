package edu.qc.seclass.glm.models;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "item", primaryKeys = {"item_name","category_name"})
public class Item implements Serializable {

    @NonNull
    @ColumnInfo
    String item_name;

    @NonNull
    @ColumnInfo
    String category_name;

    public Item(String item_name, String category_name) {
        this.item_name = item_name;
        this.category_name = category_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}

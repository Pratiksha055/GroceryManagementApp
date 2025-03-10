package edu.qc.seclass.glm.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_category")
public class ItemCategory {

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    int item_category_id;

    @ColumnInfo
    String item_category_name;

    public ItemCategory(String item_category_name) {
        this.item_category_name = item_category_name;
    }

    public int getItem_category_id() {
        return item_category_id;
    }

    public void setItem_category_id(int item_category_id) {
        this.item_category_id = item_category_id;
    }

    public String getItem_category_name() {
        return item_category_name;
    }

    public void setItem_category_name(String item_category_name) {
        this.item_category_name = item_category_name;
    }
}

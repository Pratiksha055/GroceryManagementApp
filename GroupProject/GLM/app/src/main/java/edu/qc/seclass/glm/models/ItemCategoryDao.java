package edu.qc.seclass.glm.models;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ItemCategoryDao {

    @Query("SELECT item_category_id,item_category_name FROM item_category")
    public List<ItemCategory> getAllItemCategories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertNewItemCategory(ItemCategory itemCategory);

}

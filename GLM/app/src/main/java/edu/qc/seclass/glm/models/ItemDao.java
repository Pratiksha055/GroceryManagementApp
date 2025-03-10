package edu.qc.seclass.glm.models;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ItemDao {

    @Query("SELECT item_name,category_name FROM item ORDER BY item_name ASC")
    public List<Item> getAllSearchItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertNewSearchItem(Item item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAllSearchItems(List<Item> item_list);

}

package edu.qc.seclass.glm.models;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface GroceryListItemDao {

    @Query("SELECT grocery_list_item_id,grocery_list_id,item_name,quantity,marked,grocerylist_grocery_list_id,grocerylist_grocery_list_name FROM grocery_list_item ORDER BY item_name ASC")
    public List<GroceryListItem> getAllGLItems();

    @Query("SELECT grocery_list_item_id,grocery_list_id,item_name,quantity,marked,grocerylist_grocery_list_id,grocerylist_grocery_list_name FROM grocery_list_item WHERE grocery_list_id = :grocery_list_id ORDER BY item_name ASC")
    public List<GroceryListItem> getGLItemsForGroceryList(int grocery_list_id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertNewItem(GroceryListItem item);

    @Query("SELECT COUNT(*) FROM grocery_list_item WHERE marked = 1 and grocery_list_id=:grocery_list_id")
    public int getCountItemsChecked(int grocery_list_id);

    @Query("UPDATE grocery_list_item SET marked = :marked WHERE grocery_list_item_id = :grocery_list_item_id")
    public void updateItemCheckBox(boolean marked, int grocery_list_item_id);

    @Query("UPDATE grocery_list_item SET marked = :marked WHERE grocery_list_id = :grocery_list_id")
    public void updateAllItemsCheckBox(boolean marked, int grocery_list_id);

    @Query("DELETE FROM grocery_list_item WHERE grocery_list_id = :grocery_list_id")
    public void deleteAllGLItems(int grocery_list_id);

    @Delete
    void deleteItem(GroceryListItem item);

}

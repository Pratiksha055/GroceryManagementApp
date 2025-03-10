package edu.qc.seclass.glm.models;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface GroceryListDao {

    @Query("SELECT grocery_list_id,grocery_list_name FROM grocery_list ORDER BY grocery_list_name ASC")
    public List<GroceryList> getAllGroceryLists();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertNewGroceryList(GroceryList grocery_list);

    @Query("DELETE FROM grocery_list")
    void deleteAllGroceryLists();

    @Delete
    void deleteGroceryList(GroceryList grocery_list);

    @Query("UPDATE grocery_list SET grocery_list_name = :gl_name WHERE grocery_list_id = :gl_id")
    void updateGroceryListName(String gl_name, int gl_id);

    @Update
    void updateGLName(GroceryList gl);

}

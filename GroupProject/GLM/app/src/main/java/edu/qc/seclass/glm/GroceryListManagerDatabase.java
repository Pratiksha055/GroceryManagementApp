package edu.qc.seclass.glm;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import edu.qc.seclass.glm.models.GroceryList;
import edu.qc.seclass.glm.models.GroceryListDao;
import edu.qc.seclass.glm.models.GroceryListItem;
import edu.qc.seclass.glm.models.GroceryListItemDao;
import edu.qc.seclass.glm.models.Item;
import edu.qc.seclass.glm.models.ItemCategory;
import edu.qc.seclass.glm.models.ItemCategoryDao;
import edu.qc.seclass.glm.models.ItemDao;

@Database(entities = {GroceryList.class, GroceryListItem.class, Item.class, ItemCategory.class}, version = 8)
public abstract class GroceryListManagerDatabase extends RoomDatabase {

    public abstract GroceryListDao groceryListDao();
    public abstract GroceryListItemDao groceryListItemDao();
    public abstract ItemDao itemDao();
    public abstract ItemCategoryDao itemCategoryDao();

    public static final String NAME = "GroceryListManagerDatabase";

}

package edu.qc.seclass.glm;


import android.app.Application;

import androidx.room.Room;

public class GroceryListManagerApp  extends Application {

    GroceryListManagerDatabase groceryListManagerDatabase;


    @Override
    public void onCreate() {
        super.onCreate();
        groceryListManagerDatabase = Room.databaseBuilder(this, GroceryListManagerDatabase.class, GroceryListManagerDatabase.NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public GroceryListManagerDatabase getGroceryListManagerDatabase(){
        return groceryListManagerDatabase;
    }
}

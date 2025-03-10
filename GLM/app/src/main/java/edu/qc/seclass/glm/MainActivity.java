package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.qc.seclass.glm.models.GroceryList;
import edu.qc.seclass.glm.models.GroceryListDao;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvGroceryLists;
    GroceryListsAdapter adapter;
    FloatingActionButton btnAdd;
    EditText addNewListField;
    GroceryList groceryList;
    ArrayList<GroceryList> groceryLists = new ArrayList<GroceryList>();
    GroceryListDao groceryListDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get database access
        groceryListDao = ((GroceryListManagerApp) getApplicationContext()).getGroceryListManagerDatabase().groceryListDao();

        btnAdd = findViewById(R.id.btnAdd);
        adapter = new GroceryListsAdapter(groceryLists, MainActivity.this);
        rvGroceryLists = (RecyclerView) findViewById(R.id.rvGroceryLists);
        rvGroceryLists.setAdapter(adapter);
        rvGroceryLists.setLayoutManager(new LinearLayoutManager(this));

        // asynchronously get grocery lists from DB and display in UI when launching app
        getGroceryListsFromDB();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get reference to EditText field and store contents in String var
                addNewListField = (EditText)findViewById(R.id.addNewListField);
                String newList = addNewListField.getText().toString();
                //insert new item to database
                groceryList = new GroceryList(newList);
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        groceryListDao.insertNewGroceryList(groceryList);
                    }
                });

                addNewListField.setText("");
                Toast.makeText(MainActivity.this,"New list created", Toast.LENGTH_LONG).show();

                //get all items from database and update view
                // 1. get items from database and add to list
                //groceryLists = new ArrayList<GroceryList>();

                //groceryLists.clear();

                // asynchronously get grocery lists from DB and display in UI
                getGroceryListsFromDB();

/*                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        // get lists from db and log
                        Log.i("getallgrocerylists", groceryListDao.getAllGroceryLists().get(1).getGroceryListName());
                        groceryLists.clear();
                        //get lists from DB and add to list for displaying in UI
                        groceryLists.addAll(groceryListDao.getAllGroceryLists());

                        // used for testing list population from DB
                        for (int i = 0;i<groceryLists.size();i++) {
                            Log.i("grocerylistname", groceryLists.get(i).getGroceryListName());
                        }
                        // testing list size
                        Log.i("getallgrocerylists", "size " + groceryLists.size());
                        // 2. display items from list using recyclerview

                        adapter.addAll(0,
                                groceryLists);
                        //Log.i("MainActivity test", (String) adapter.toString());
                        //Log.i("MainAct grocerylist", groceryLists.get(0).toString());
                        adapter.notifyDataSetChanged();

                    }
                });*/
            }
        });
    }

    public void getGroceryListsFromDB(){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // get lists from db and log
                //Log.i("getallgrocerylists", groceryListDao.getAllGroceryLists().get(1).getGroceryListName());
                //adapter.clear();
                groceryLists.clear();
                //get lists from DB and add to list for displaying in UI
                groceryLists.addAll(groceryListDao.getAllGroceryLists());

                // used for testing list population from DB
                //for (int i = 0;i<groceryLists.size();i++) {
                  //  Log.i("grocerylistname", groceryLists.get(i).getGroceryListName());
                //}
                // testing list size
                //Log.i("getallgrocerylists", "size " + groceryLists.size());
                // 2. display items from list using recyclerview

                //adapter.addAll(0,groceryLists);
                //Log.i("MainActivity test", (String) adapter.toString());
                //Log.i("MainAct grocerylist", groceryLists.get(0).toString());
                //adapter.notifyDataSetChanged();

            }

        });
        adapter.notifyDataSetChanged();
    }

}
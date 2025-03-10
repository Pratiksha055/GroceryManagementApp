package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.qc.seclass.glm.models.GroceryList;
import edu.qc.seclass.glm.models.GroceryListDao;
import edu.qc.seclass.glm.models.GroceryListItem;
import edu.qc.seclass.glm.models.GroceryListItemDao;
import edu.qc.seclass.glm.models.Item;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class AddViewGLItemsActivity extends AppCompatActivity implements GroceryListItemAdapter.ItemCheckedChangeListener {

    FloatingActionButton btnAdd;
    TextView tvShoppingListName;
    Button btnUnCheckAll;
    RecyclerView rvItems;
    int grocery_list_id;
    GroceryList grocery_list;
    Item item;
    GroceryListItemAdapter glItemAdapter;
    ArrayList<GroceryList> groceryLists = new ArrayList<GroceryList>();
    ArrayList<GroceryListItem> groceryItems = new ArrayList<GroceryListItem>();
    GroceryListItem groceryItem;
    GroceryListDao groceryListDao;
    GroceryListItemDao groceryListItemDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_gl_items);

        // get database access
        groceryListDao = ((GroceryListManagerApp) getApplicationContext()).getGroceryListManagerDatabase().groceryListDao();
        groceryListItemDao = ((GroceryListManagerApp) getApplicationContext()).getGroceryListManagerDatabase().groceryListItemDao();

        glItemAdapter = new GroceryListItemAdapter(groceryItems, AddViewGLItemsActivity.this, this::selectedItemCheckedChange);
        rvItems = (RecyclerView) findViewById(R.id.rvItems);
        rvItems.setAdapter(glItemAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        tvShoppingListName = findViewById(R.id.tvShoppingListName);
        btnAdd = findViewById(R.id.btnAdd);
        btnUnCheckAll = findViewById(R.id.btnUncheckAll);

        btnUnCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(glItemAdapter.getCheckedItemCount()>0){
                    //uncheckAllCheckBoxes();
                    for(GroceryListItem i : glItemAdapter.groceryItems){
                        i.setMarked(false);
                    }
                    glItemAdapter.notifyDataSetChanged();
                }
            }
        });

        //get grocery lists from db and add to array list so we can search for list with selected id passed from MainActivity
        Intent intent = getIntent();
        grocery_list_id = intent.getIntExtra("grocery_list_id", -1);

        //countCheckedGroceryItems = groceryListItemDao.getCountItemsChecked(grocery_list_id);

        MyAsyncTask asyncTask=new MyAsyncTask();
        asyncTask.execute(groceryLists);

        getGroceryItemsFromDB();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddViewGLItemsActivity.this, SearchForItemsActivity.class);
                intent.putExtra("grocery_list_id", grocery_list_id);
                intent.putExtra("grocery_items_list",(Serializable) groceryItems);
                startActivity(intent);
            }
        });
    }

    private void uncheckAllCheckBoxes() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                groceryListItemDao.updateAllItemsCheckBox(false,grocery_list_id);
                groceryItems.clear();
                //get lists from DB and add to list for displaying in UI
                groceryItems.addAll(groceryListItemDao.getGLItemsForGroceryList(grocery_list_id));

            }

        });
        //glItemAdapter.notifyDataSetChanged();

    }


    private class MyAsyncTask extends AsyncTask<ArrayList<GroceryList>, Void, Void> {
        @Override
        protected Void doInBackground(ArrayList<GroceryList>... params) {

            groceryLists.addAll(groceryListDao.getAllGroceryLists());

            return null;
        }


        protected void onPostExecute(Void result) {
            //Log.i("GroceryListAsynch","GroceryList " + result);
            if(grocery_list_id >= 0){
                for (GroceryList gl : groceryLists) {
                    if(gl.getGroceryListId() == grocery_list_id){
                        grocery_list = gl;
                    }
                }
                tvShoppingListName.setText(grocery_list.getGroceryListName());
            }

        }

    }

    public void getGroceryItemsFromDB(){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                groceryItems.clear();
                //get lists from DB and add to list for displaying in UI
                groceryItems.addAll(groceryListItemDao.getGLItemsForGroceryList(grocery_list_id));

            }

        });
        glItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void selectedItemCheckedChange(GroceryListItem grocery_item){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Log.i("GroceryItem Activity", "GroceryItem isMarked: " + grocery_item.isMarked());
                groceryListItemDao.updateItemCheckBox(grocery_item.isMarked(),grocery_item.getGrocery_list_item_id());
            }
        });

    }

}
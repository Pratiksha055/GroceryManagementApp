package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.qc.seclass.glm.models.GroceryList;
import edu.qc.seclass.glm.models.GroceryListItem;
import edu.qc.seclass.glm.models.Item;
import edu.qc.seclass.glm.models.ItemCategoryDao;
import edu.qc.seclass.glm.models.ItemDao;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchForItemsActivity extends AppCompatActivity implements SearchForItemsAdapter.ItemClickListener, AdapterView.OnItemSelectedListener{

    RecyclerView rvItemChoices;
    EditText etAddNewSearchOption;
    FloatingActionButton fabAddNewSearchOption;
    ItemDao itemDao;
    ItemCategoryDao itemCategoryDao;
    SearchForItemsAdapter adapter;
    List<Item> item_list = new ArrayList<>();
    SearchView searchView;
    Spinner spinnerCategory;
    String selectedCategory;
    int grocerylist_id;
    List<GroceryListItem> groceryItems = new ArrayList<>();
    Item newItem;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_items);
        itemDao = ((GroceryListManagerApp) getApplicationContext()).getGroceryListManagerDatabase().itemDao();
        itemCategoryDao = ((GroceryListManagerApp) getApplicationContext()).getGroceryListManagerDatabase().itemCategoryDao();
        rvItemChoices = findViewById(R.id.rvItemChoices);
        adapter = new SearchForItemsAdapter(item_list,this, this::selectedItem);
        prepareRecyclerView();

        searchView = findViewById(R.id.searchView);
        etAddNewSearchOption = findViewById(R.id.etAddNewSearchOption);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.categories,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(arrayAdapter);
        spinnerCategory.setOnItemSelectedListener(this);
        fabAddNewSearchOption = findViewById(R.id.fabAddNewSearchOption);
        searchView.clearFocus();
        intent = getIntent();
        grocerylist_id = intent.getIntExtra("grocery_list_id", -1);
        groceryItems = (List<GroceryListItem>) intent.getSerializableExtra("grocery_items_list");

        setData();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        fabAddNewSearchOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // capture text for new search item to add

                // capture text for spinning wheel option to add

                // Create Item and insert new item record to db

                // create intent and forward user to EditItemActivity and pass item in intent (or just item and item category)
                // can call selectedItem(item) to do this and pass newly created item
                createNewItem();

            }
        });


    }

    private void filterList(String text) {
        List<Item> filteredList = new ArrayList<>();
        for (Item item : item_list){
            if(item.getItem_name().toLowerCase().contains(text.toLowerCase()) || item.getCategory_name().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        if(filteredList.isEmpty()){
            Toast.makeText(this, "No matching items", Toast.LENGTH_SHORT).show();
        }
        else{
            adapter.setFilteredList(filteredList);
        }
    }

    public void setData(){

            Log.i("setdata","NoItems found from DB. Adding Items");
            item_list.add(new Item("Sandwich Bread", "Bakery and Breads"));
            item_list.add(new Item("Milk", "Dairy"));
            item_list.add(new Item("Eggs","Meat and Poultry"));
            item_list.add(new Item("Cheese","Dairy"));
            item_list.add(new Item("Ground Beef","Meat and Poultry"));
            item_list.add(new Item("Chicken Breast","Meat and Poultry"));
            item_list.add(new Item("Chicken Thighs","Meat and Poultry"));
            item_list.add(new Item("Ground Pork","Meat and Poultry"));
            item_list.add(new Item("Potatoes","Vegetables"));
            item_list.add(new Item("Corn","Vegetables"));
            item_list.add(new Item("Orange Juice","Fruit Juices"));
            item_list.add(new Item("Apple Juice","Poultry"));
            item_list.add(new Item("Rice", "Grains"));
            item_list.add(new Item("Apples", "Fruits"));
            item_list.add(new Item("Oranges", "Fruits"));
            item_list.add(new Item("Mangoes", "Fruits"));
            item_list.add(new Item("Strawberries", "Fruits"));
            item_list.add(new Item("Blueberries", "Fruits"));
            item_list.add(new Item("Raspberries", "Fruits"));
            item_list.add(new Item("Yogurt", "Dairy"));
            item_list.add(new Item("Cucumber", "Vegetables"));
            item_list.add(new Item("Broccoli", "Vegetables"));
            item_list.add(new Item("Paper Towels", "Paper Products"));
            item_list.add(new Item("Napkins", "Paper Products"));
            item_list.add(new Item("Apples", "Fruits"));
            item_list.add(new Item("Dish Detergent", "Cleaning Products"));
            item_list.add(new Item("Soap", "Cleaning Products"));
            item_list.add(new Item("Wine", "Alcoholic Beverages"));
            item_list.add(new Item("Beer", "Alcoholic Beverages"));

            insertSearchItems();

            //getRefreshedSearchItemListFromDB();

    }

    public void prepareRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvItemChoices.setLayoutManager(linearLayoutManager);
        prepareAdapter();
    }

    public void prepareAdapter(){

        rvItemChoices.setAdapter(adapter);
    }

    @Override
    public void selectedItem(Item item) {
        Toast.makeText(this, "Selected item: "+item.getItem_name()+" Category: "+item.getCategory_name(),Toast.LENGTH_SHORT).show();
        Intent intent2 = new Intent(SearchForItemsActivity.this, EditItemActivity.class);
        intent2.putExtra("grocery_list_id", grocerylist_id);
        intent2.putExtra("grocery_list_items",(Serializable) groceryItems);
        intent2.putExtra("data",item);
        startActivity(intent2);
        finish();
    }

    public void createNewItem(){

        newItem = new Item(etAddNewSearchOption.getText().toString(),selectedCategory);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // get lists from db and log
                //Log.i("getallgrocerylists", groceryListDao.getAllGroceryLists().get(1).getGroceryListName());
                //adapter.clear();
                itemDao.insertNewSearchItem(newItem);

            }
        });

        Intent intent2 = new Intent(SearchForItemsActivity.this, EditItemActivity.class);
        intent2.putExtra("grocery_list_id", grocerylist_id);
        intent2.putExtra("grocery_list_items", (Serializable) groceryItems);
        intent2.putExtra("data",newItem);
        startActivity(intent2);
        finish();
    }

    public void insertSearchItems(){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // get lists from db and log
                //Log.i("getallgrocerylists", groceryListDao.getAllGroceryLists().get(1).getGroceryListName());
                //adapter.clear();
                itemDao.insertAllSearchItems(item_list);

            }
        });

        getInitialSearchItemListFromDB();
    }

    public void getInitialSearchItemListFromDB(){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // get lists from db and log
                //Log.i("getallgrocerylists", groceryListDao.getAllGroceryLists().get(1).getGroceryListName());
                //adapter.clear();
                item_list.clear();
                //get lists from DB and add to list for displaying in UI
                item_list.addAll(itemDao.getAllSearchItems());

            }
        });
        adapter.notifyDataSetChanged();
    }


    public void getRefreshedSearchItemListFromDB(){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // get lists from db and log
                //Log.i("getallgrocerylists", groceryListDao.getAllGroceryLists().get(1).getGroceryListName());
                //adapter.clear();
                item_list.clear();
                //get lists from DB and add to list for displaying in UI
                item_list.addAll(itemDao.getAllSearchItems());

            }
        });
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedCategory = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selectedCategory = "Other";
    }
}
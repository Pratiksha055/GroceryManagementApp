package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;
import edu.qc.seclass.glm.models.GroceryList;
import edu.qc.seclass.glm.models.GroceryListItem;
import edu.qc.seclass.glm.models.GroceryListItemDao;
import edu.qc.seclass.glm.models.Item;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class EditItemActivity extends AppCompatActivity {

    TextView tvSelectedItem;
    NumberPicker npQty;
    Button btnSaveItem;
    Item item;
    Intent intent;
    Intent intent2;
    int grocerylist_id, quantity;
    GroceryListItem groceryListItem;
    List<GroceryListItem> groceryItems;
    GroceryListItemDao groceryListItemDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        tvSelectedItem = findViewById(R.id.tvSelectedItem);
        // connect to the database so we can do insert when save button is clicked
        groceryListItemDao = ((GroceryListManagerApp) getApplicationContext()).getGroceryListManagerDatabase().groceryListItemDao();
        npQty = findViewById(R.id.npQty);
        npQty.setMaxValue(50);
        npQty.setMinValue(1);
        npQty.setValue(1);
        quantity = 1;
        btnSaveItem = findViewById(R.id.btnSaveItem);

        npQty.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(final NumberPicker numberPicker, final int i, final int i1) {
                quantity = numberPicker.getValue();
                Log.i("numberpicker","value: " + quantity);
            }
        });

        intent = getIntent();

        if(intent != null){
            item = (Item) intent.getSerializableExtra("data");
            groceryItems = (List<GroceryListItem>) intent.getSerializableExtra("grocery_list_items");
            grocerylist_id = intent.getIntExtra("grocery_list_id", -1);
            String item_name = item.getItem_name();
            tvSelectedItem.setText(item_name);

            btnSaveItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.i("onclick np","value: " + quantity);
                    // insert to database
                    // 1.  send quantity from number picker and create a GroceryListItem
                    //quantity = npQty.getValue();
                    groceryListItem = new GroceryListItem(item_name,grocerylist_id,quantity,false);
                    // 2.  insert record to correct grocery list based upon grocery_list_id filter
                    if(!containsItemName((List<GroceryListItem>) groceryItems, item_name)){
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                groceryListItemDao.insertNewItem(groceryListItem);
                            }
                        });
                    }
                    else{
                        Toast.makeText(EditItemActivity.this,"Item Already Previously Added to this List", Toast.LENGTH_LONG).show();

                    }
                    //send user back to list of items to view grocery list they were previously adding items onto
                    Intent intent2 = new Intent(EditItemActivity.this, AddViewGLItemsActivity.class);
                    intent2.putExtra("grocery_list_id", grocerylist_id);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent2);
                    finish();
                }
            });
        }
    }

    public static boolean containsItemName(Collection<GroceryListItem> c, String itemName) {
        for(GroceryListItem o : c) {
            if(o != null && o.getItem_name().toLowerCase().equals(itemName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
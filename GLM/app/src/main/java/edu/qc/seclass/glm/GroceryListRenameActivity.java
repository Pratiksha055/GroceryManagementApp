package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;
import edu.qc.seclass.glm.models.GroceryList;
import edu.qc.seclass.glm.models.GroceryListDao;
import edu.qc.seclass.glm.models.GroceryListItem;
import edu.qc.seclass.glm.models.Item;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

public class GroceryListRenameActivity extends AppCompatActivity {

    Button btnSave;
    EditText etListName;
    GroceryList groceryList;
    Intent intent;

    ArrayList<GroceryList> groceryLists = new ArrayList<GroceryList>();
    GroceryListDao groceryListDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groceryListDao = ((GroceryListManagerApp) getApplicationContext()).getGroceryListManagerDatabase().groceryListDao();

        setContentView(R.layout.activity_grocery_list_rename);
        etListName = findViewById(R.id.etListName);
        int grocerylist_id;
        btnSave = findViewById(R.id.btnSave);
        intent = getIntent();

        if(intent != null){
            groceryList = (GroceryList) intent.getSerializableExtra("grocery_list");
            grocerylist_id = intent.getIntExtra("grocery_list_id", -1);
            String item_name = groceryList.getGroceryListName();

            Log.i("glrenameactivity if", "glname: " + groceryList.getGroceryListName());
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // insert to database
                    // 1.  send quantity from number picker and create a GroceryListItem
                    String groceryListName;
                    groceryListName = etListName.getText().toString();
                    Log.i("onclick", "glname_onclick: " + groceryListName);
                    groceryList.setGroceryListName(groceryListName);

                     // 2.  insert record to correct grocery list based upon grocery_list_id filter
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("glName_async: ", "glname_async" + groceryListName);
                            groceryListDao.updateGLName(groceryList);
                        }
                    });

                    //send user back to list of items to view grocery list they were previously adding items onto
                    Intent intent2 = new Intent(GroceryListRenameActivity.this, MainActivity.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent2);
                    finish();
                }
            });
        }

    }
}
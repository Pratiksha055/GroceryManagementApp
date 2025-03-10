package edu.qc.seclass.glm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import edu.qc.seclass.glm.models.GroceryList;
import edu.qc.seclass.glm.models.GroceryListItem;
import edu.qc.seclass.glm.models.GroceryListItemDao;
import kotlin.jvm.internal.markers.KMutableList;


public class GroceryListItemAdapter extends RecyclerView.Adapter<GroceryListItemAdapter.ViewHolder>{

    Context context;
    public ArrayList<GroceryListItem> groceryItems;
    ItemCheckedChangeListener itemCheckedChangeListener;
    GroceryListItemDao groceryListItemDao;

    public GroceryListItemAdapter(ArrayList<GroceryListItem> groceryItems, Context context, ItemCheckedChangeListener itemCheckedChangeListener) {
        this.groceryItems = groceryItems;
        this.context = context;
        this.itemCheckedChangeListener = itemCheckedChangeListener;
    }

    public interface ItemCheckedChangeListener {
        void selectedItemCheckedChange(GroceryListItem groceryItem);
    }

    @NonNull
    @Override
    public GroceryListItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View GroceryItemsView = inflater.inflate(R.layout.add_view_gl_item_row,parent,false);
        GroceryListItemAdapter.ViewHolder viewHolder = new GroceryListItemAdapter.ViewHolder(GroceryItemsView);
        groceryListItemDao = ((GroceryListManagerApp) context.getApplicationContext()).getGroceryListManagerDatabase().groceryListItemDao();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryListItemAdapter.ViewHolder holder, int position) {
        GroceryListItem currentGroceryItem = groceryItems.get(position);
        TextView tvGroceryItem = holder.tvGroceryItem;
        CheckBox checkBox = holder.checkBox;
        TextView tvQuantity = holder.tvQuantity;
        FloatingActionButton fabDeleteGroceryItem = holder.fabDeleteGroceryItem;
        //set checked state from database
        tvGroceryItem.setText(currentGroceryItem.getItem_name());
        tvQuantity.setText(Integer.toString(currentGroceryItem.getQuantity()));
        Log.i("ISMarkedValue", "IsMarked: " + currentGroceryItem.isMarked());
        checkBox.setChecked(currentGroceryItem.isMarked());

        fabDeleteGroceryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteGroceryItem(currentGroceryItem,holder.getAdapterPosition());
            }
        });

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox.isChecked()){
                    currentGroceryItem.setMarked(true);
                }
                else{
                    currentGroceryItem.setMarked(false);
                }
                itemCheckedChangeListener.selectedItemCheckedChange(currentGroceryItem);
            }
        });

    }

    private void deleteGroceryItem(GroceryListItem gi, int position) {
        groceryItems.remove(gi);
        groceryListItemDao.deleteItem(gi);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return groceryItems.size();
    }

    public int getCheckedItemCount(){
        List<GroceryListItem> countCheckedItems = new ArrayList<>();
        if(!groceryItems.isEmpty()){
            for(GroceryListItem gi : groceryItems){
                if(gi.isMarked() == true){
                    countCheckedItems.add(gi);
                }
            }
        }
        return countCheckedItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvGroceryItem;
        public TextView tvQuantity;
        public CheckBox checkBox;
        public FloatingActionButton fabDeleteGroceryItem;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGroceryItem = itemView.findViewById(R.id.tvGroceryItem);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            fabDeleteGroceryItem = itemView.findViewById(R.id.fabDeleteGroceryItem);
            parentLayout = itemView.findViewById(R.id.AddEditGLItemsRowLayout);

        }
    }

    public void clear(){
        groceryItems.clear();
        notifyDataSetChanged();
    }
    public void addAll(int pos, List<GroceryListItem> groceryItems){
        groceryItems.addAll(pos,groceryItems);
        notifyDataSetChanged();
    }
}

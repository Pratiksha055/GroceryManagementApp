package edu.qc.seclass.glm;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import edu.qc.seclass.glm.models.GroceryList;
import edu.qc.seclass.glm.models.GroceryListDao;
import edu.qc.seclass.glm.models.GroceryListItemDao;

public class GroceryListsAdapter extends RecyclerView.Adapter<GroceryListsAdapter.ViewHolder> {

    Context context;
    GroceryListDao groceryListDao;
    GroceryListItemDao groceryListItemDao;

    public interface OnListLongClickListener {
        void selectedList(GroceryList groceryList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvListName;
        public FloatingActionButton fabDeleteGroceryList;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvListName = (TextView) itemView.findViewById(R.id.tvListName);
            fabDeleteGroceryList = itemView.findViewById(R.id.fabDeleteGroceryList);
            parentLayout = itemView.findViewById(R.id.itemGroceryListLayout);
        }

    }

    @Override
    public GroceryListsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        groceryListDao = ((GroceryListManagerApp) context.getApplicationContext()).getGroceryListManagerDatabase().groceryListDao();
        groceryListItemDao = ((GroceryListManagerApp) context.getApplicationContext()).getGroceryListManagerDatabase().groceryListItemDao();
        LayoutInflater inflater = LayoutInflater.from(context);
        View GroceryListsView = inflater.inflate(R.layout.item_grocery_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(GroceryListsView);

        return viewHolder;
    }

    public ArrayList<GroceryList> groceryLists;

    public GroceryListsAdapter(ArrayList<GroceryList> groceryLists, Context context) {
        this.groceryLists = groceryLists;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(GroceryListsAdapter.ViewHolder holder, int position) {
        Log.i("onbindviewholder","in on bind viewholder");
        GroceryList currentGroceryList = groceryLists.get(position);
        TextView textView = holder.tvListName;
        FloatingActionButton fabDeleteGroceryList = holder.fabDeleteGroceryList;
        textView.setText(currentGroceryList.getGroceryListName());
        Log.i("GroceryListAdapter", (String) textView.getText());

        fabDeleteGroceryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteGroceryList(currentGroceryList, holder.getAdapterPosition());
            }
        });
        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //send control to GroceryListReameActivity

                Intent intent = new Intent(context, GroceryListRenameActivity.class);
                intent.putExtra("grocery_list_id", currentGroceryList.getGroceryListId());
                intent.putExtra("grocery_list", currentGroceryList);
                context.startActivity(intent);
                return false;
            }
        });
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send control to Add/Edit Grocery List Items Activity
                Intent intent = new Intent(context, AddViewGLItemsActivity.class);
                intent.putExtra("grocery_list_id", currentGroceryList.getGroceryListId());
                context.startActivity(intent);
            }
        });
    }

    private void deleteGroceryList(GroceryList currentGroceryList, int adapterPosition) {
        groceryListItemDao.deleteAllGLItems(currentGroceryList.getGroceryListId());
        groceryLists.remove(currentGroceryList);
        groceryListDao.deleteGroceryList(currentGroceryList);
        notifyItemChanged(adapterPosition);
        notifyDataSetChanged();
    }


    public void clear(){
        groceryLists.clear();
        notifyDataSetChanged();
    }
    public void addAll(int pos, List<GroceryList> groceryList){
        groceryLists.addAll(pos,groceryList);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        //Log.i("adapter itemcount:", "count " + groceryLists.size());
        return this.groceryLists.size();
    }
}

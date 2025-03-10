package edu.qc.seclass.glm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;
import edu.qc.seclass.glm.models.Item;

public class SearchForItemsAdapter extends RecyclerView.Adapter<SearchForItemsAdapter.ViewHolder> {

    public List<Item> item_list;
    public Context context;

    public ItemClickListener itemClickListener;

    public void setFilteredList(List<Item> filteredList){
        this.item_list = filteredList;
        notifyDataSetChanged();
    }

    public interface ItemClickListener{
        void selectedItem(Item item);
    }

    public SearchForItemsAdapter(List<Item> item_list, Context context, ItemClickListener itemClickListener) {
        this.item_list = item_list;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_row, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i("onbind", "onbind items position: " + position);
        Item currentItem = item_list.get(position);
        String itemName = currentItem.getItem_name();
        holder.tvItemName.setText(itemName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.selectedItem(currentItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.item_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvItemName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvItemName);

        }
    }
}

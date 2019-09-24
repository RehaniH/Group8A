package com.example.group8a;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context mcontext;
    private List<Product> muploads;
    private OnItemClickListener mListener;


    public ItemAdapter(Context context, List<Product> uploads) {
        this.mcontext = context;
        this.muploads = uploads;


    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.product_item, parent, false);
        return new ItemViewHolder(view);
    }
//.placeholder(R.mipmap.ic_launcher)
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Product uploadCurrent = muploads.get(position);
        Picasso.with(mcontext)
                .load(uploadCurrent.getmUri())
                .placeholder(R.drawable.ic_image_black_24dp)
                .fit()
                .centerCrop()
                .into(holder.imageView);
        holder.textViewName.setText(uploadCurrent.getName());
        holder.textViewColor.setText(uploadCurrent.getColor());

    }

    @Override
    public int getItemCount() {
        return muploads.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public TextView textViewName;
        public TextView textViewColor;
        public ImageView imageView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_view);
            textViewName = itemView.findViewById(R.id.product_name);
            textViewColor = itemView.findViewById(R.id.product_color);

            //Remove if not needed
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mListener != null){
                int position = getAdapterPosition();

                if (position != RecyclerView.NO_POSITION){
                    mListener.OnItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Select action");
            MenuItem doWhatever = contextMenu.add(Menu.NONE, 1, 1, "Update");
            MenuItem delete = contextMenu.add(Menu.NONE, 2, 2, "Delete");
            doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {

            if (mListener != null){
                int position = getAdapterPosition();

                if (position != RecyclerView.NO_POSITION){

                    switch (menuItem.getItemId()){

                        case 1: mListener.OnUpdateClick(position);
                            return true;
                        case 2: mListener.OnDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }//end class

    public interface OnItemClickListener{

        void OnItemClick( int position);
        void OnUpdateClick( int position);
        void OnDeleteClick( int position);


    }
    public void setOnClickItemListener(OnItemClickListener listener){
        mListener = listener;
    }


}

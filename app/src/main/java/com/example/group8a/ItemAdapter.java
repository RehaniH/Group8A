package com.example.group8a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context;
    private List<UploadImage> uploads;

    public ItemAdapter(Context context, List<UploadImage> uploads) {
        this.context = context;
        this.uploads = uploads;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        UploadImage uploadCurrent = uploads.get(position);
        holder.textViewName.setText(uploadCurrent.getImageData());
        Picasso.with(context)
                .load(uploadCurrent.getImageUri())
                .fit()
                .centerCrop()
                .into(holder.imageViewName);
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewName;
        public ImageView imageViewName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.product_name);
            imageViewName = itemView.findViewById(R.id.product_view);
        }
    }

}

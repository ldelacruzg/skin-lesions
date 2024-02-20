package com.example.skinlesions.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skinlesions.Models.Lesion;
import com.example.skinlesions.R;

import java.util.List;

public class ListLesionAdapter extends RecyclerView.Adapter<ListLesionAdapter.ViewHolder> {
    List<Lesion> lesionList;
    int layout;
    OnItemClickListener itemClickListener;

    public ListLesionAdapter(List<Lesion> lesionList, int layout, OnItemClickListener itemClickListener) {
        this.lesionList = lesionList;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(lesionList.get(position), this.itemClickListener);
    }

    @Override
    public int getItemCount() {
        return this.lesionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }

        public void bind(Lesion lesion, OnItemClickListener listener) {
            textViewName.setText(lesion.getName());
            textViewDescription.setText(lesion.getDescription());
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(lesion, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Lesion lesion, int position);
    }
}

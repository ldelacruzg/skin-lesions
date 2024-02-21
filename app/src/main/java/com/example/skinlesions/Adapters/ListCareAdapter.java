package com.example.skinlesions.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skinlesions.Models.Symptom;
import com.example.skinlesions.R;

import java.util.List;

public class ListCareAdapter extends RecyclerView.Adapter<ListCareAdapter.ViewHolder> {
    List<String> care;
    int layout;

    public ListCareAdapter(List<String> care, int layout) {
        this.care = care;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ListCareAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);

        return new ListCareAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCareAdapter.ViewHolder holder, int position) {
        holder.bind(care.get(position), position);
    }

    @Override
    public int getItemCount() {
        return care.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewTitle);
            this.textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }

        public void bind(String care, int position) {
            this.textViewName.setText("Cuidado " + (position + 1));
            this.textViewDescription.setText(care);
        }

    }
}

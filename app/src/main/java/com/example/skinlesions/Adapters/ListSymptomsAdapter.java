package com.example.skinlesions.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skinlesions.Models.Lesion;
import com.example.skinlesions.Models.Symptom;
import com.example.skinlesions.R;

import java.util.List;

public class ListSymptomsAdapter extends RecyclerView.Adapter<ListSymptomsAdapter.ViewHolder> {
    List<Symptom> symptoms;
    int layout;

    public ListSymptomsAdapter(List<Symptom> symptoms, int layout) {
        this.symptoms = symptoms;
        this.layout = layout;
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
        holder.bind(symptoms.get(position));
    }

    @Override
    public int getItemCount() {
        return symptoms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewTitle);
            this.textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }

        public void bind(Symptom symptom) {
            this.textViewName.setText(symptom.getTitle());
            this.textViewDescription.setText(symptom.getDescription());
        }
    }
}

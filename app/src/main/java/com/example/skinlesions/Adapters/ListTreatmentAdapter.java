package com.example.skinlesions.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skinlesions.Models.FAQ;
import com.example.skinlesions.Models.Symptom;
import com.example.skinlesions.Models.Treatment;
import com.example.skinlesions.R;

import java.util.List;

public class ListTreatmentAdapter extends RecyclerView.Adapter<ListTreatmentAdapter.ViewHolder> {
    List<Treatment> treatmentList;
    int layout;

    public ListTreatmentAdapter(List<Treatment> treatmentList, int layout) {
        this.treatmentList = treatmentList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ListTreatmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);

        return new ListTreatmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTreatmentAdapter.ViewHolder holder, int position) {
        holder.bind(treatmentList.get(position));
    }

    @Override
    public int getItemCount() {
        return treatmentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textQuestion, textAnswer;
        LinearLayout layoutQuestion, layoutAnswer;
        ImageView imageViewArrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textQuestion = itemView.findViewById(R.id.text_question);
            textAnswer = itemView.findViewById(R.id.text_answer);
            layoutQuestion = itemView.findViewById(R.id.layout_question);
            layoutAnswer = itemView.findViewById(R.id.layout_answer);
            imageViewArrow = itemView.findViewById(R.id.image_arrow);
        }

        public void bind(Treatment treatment) {
            textQuestion.setText(treatment.getTitle());
            textAnswer.setText(treatment.getDescription());

            layoutQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (layoutAnswer.getVisibility() == View.GONE) {
                        layoutAnswer.setVisibility(View.VISIBLE);
                        imageViewArrow.setImageResource(R.drawable.ic_arrow_up);
                    } else {
                        layoutAnswer.setVisibility(View.GONE);
                        imageViewArrow.setImageResource(R.drawable.ic_arrow_down);
                    }
                }
            });
        }
    }
}

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
import com.example.skinlesions.R;

import java.util.List;

public class ListFAQAdapter extends RecyclerView.Adapter<ListFAQAdapter.ViewHolder> {
    private List<FAQ> faqList;

    public ListFAQAdapter(List<FAQ> faqList) {
        this.faqList = faqList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_faq, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(faqList.get(position));
    }

    @Override
    public int getItemCount() {
        return faqList.size();
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

        public void bind(FAQ faq) {
            textQuestion.setText(faq.getAsk());
            textAnswer.setText(faq.getAnswer());

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

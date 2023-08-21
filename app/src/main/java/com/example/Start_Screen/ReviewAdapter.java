package com.example.Start_Screen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<String> reviewData = new ArrayList<>();

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false); // 수정된 레이아웃 이름 사용
        return new ReviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        String review = reviewData.get(position);
        holder.reviewTextView.setText(review);
    }

    @Override
    public int getItemCount() {
        return reviewData.size();
    }

    public void setReviewData(List<String> data) {
        reviewData = data;
        notifyDataSetChanged();
    }

    public List<String> getReviewData() {
        return reviewData;
    }

    public void addReview(String review) {
        reviewData.add(review);
        notifyDataSetChanged();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView reviewTextView;

        ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            reviewTextView = itemView.findViewById(R.id.textViewReview); // 수정된 뷰 ID 사용
        }
    }
}

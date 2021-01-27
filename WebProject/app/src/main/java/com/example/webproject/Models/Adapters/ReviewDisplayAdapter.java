package com.example.webproject.Models.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webproject.Models.CouponDisplayingModelClass.CouponProductDetails;
import com.example.webproject.Models.ReviewDisplay.ReviewDetails;
import com.example.webproject.R;

import java.util.ArrayList;

public class ReviewDisplayAdapter extends RecyclerView.Adapter<ReviewDisplayAdapter.MyViewHolder> {

    ArrayList<ReviewDetails> reviewProductDetails;
    private Context context;

    public ReviewDisplayAdapter(ArrayList<ReviewDetails> couponProductDetails, Context context) {
        this.reviewProductDetails = couponProductDetails;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_recyclerview, parent, false);
//        RevieDetails.MyViewHolder vh = new ReviewDetails().MyViewHolder(v);
        ReviewDisplayAdapter.MyViewHolder vh = new ReviewDisplayAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        float s=reviewProductDetails.get(position).getRating();
        String x=String.valueOf(s);
        holder.nameText.setText(reviewProductDetails.get(position).getName());
        holder.descriptionText.setText(reviewProductDetails.get(position).getDescription());
        holder.ratingText.setText(x);
    }

    @Override
    public int getItemCount() {
        return reviewProductDetails.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, descriptionText, ratingText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.name);
            descriptionText = itemView.findViewById(R.id.description);
            ratingText = itemView.findViewById(R.id.rating);

        }
    }

}

package com.example.webproject.Models.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webproject.Activities.Coupons;
import com.example.webproject.Activities.ReviewActivity;
import com.example.webproject.Activities.ReviewDisplay;
import com.example.webproject.Default.RetrofitClient;
import com.example.webproject.Models.ItemsModelClass.ProductDetails;
import com.example.webproject.R;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArrayAdapter extends RecyclerView.Adapter<ArrayAdapter.MyViewHolder> {
    ArrayList<ProductDetails> productDetails;
    private Context context;
    String reviewUrl = "";
    String displayRatingUrl = "";
    String url = "";
    int productId;
    String coupenText, sendReviewText, displayRatingText;


    public ArrayAdapter(Context context, ArrayList<ProductDetails> productDetails) {
        this.productDetails = productDetails;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);
        ArrayAdapter.MyViewHolder vh = new ArrayAdapter.MyViewHolder(v);
//      View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_details,parent,false);
        return vh;
    }

    @Override
    public int getItemCount() {
        return productDetails.size();
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.details_text.setText(productDetails.get(position).getName());
//        productId = productDetails.get(position).getId();
//        ratingText = String.valueOf(productId);



        //REVIEW BUTTON
        holder.reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productId = productDetails.get(position).getId();
                sendReviewText = String.valueOf(productId);

                reviewUrl = RetrofitClient.getInstance().BASE_URL.concat(getEndPointForReview());

                Call<ResponseBody> call = RetrofitClient.getInstance().getApi().hittingReview(reviewUrl);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Intent i = new Intent(context, ReviewActivity.class);
                        i.putExtra("reviewUrl", reviewUrl);
                        i.putExtra("id", productDetails.get(position).getId());

                        context.startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        //RATING BUTTON

        holder.ratingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productId = productDetails.get(position).getId();
                displayRatingText = String.valueOf(productId);
                displayRatingUrl = RetrofitClient.getInstance().BASE_URL.concat(getEndPointForDisplayRating());

                Intent i = new Intent(context, ReviewDisplay.class);
                i.putExtra("ratingUrl", displayRatingUrl);
                context.startActivity(i);
            }
        });

        // TEXTITEMCLICK

        holder.details_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productId = productDetails.get(position).getId();
                coupenText = String.valueOf(productId);

                url = RetrofitClient.getInstance().BASE_URL.concat(getEndPointForCoupon());
                Call<ResponseBody> call = RetrofitClient.getInstance().getApi().coupon(url);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        try {
                            Intent i = new Intent(context, Coupons.class);
                            i.putExtra("title", productDetails.get(position).getName());
                            i.putExtra("id", productDetails.get(position).getId());
                            i.putExtra("url", url);
                            context.startActivity(i);

//                            String s = response.body().toString();
                            //  Toast.makeText(context, s, Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    private String getEndPointForDisplayRating() {
        String endPoint = "reviews/";
        String variableEndPoint = displayRatingText.concat("/reviews");
        return endPoint.concat(variableEndPoint);
    }


    private String getEndPointForReview() {
        String endPoint = "reviews/";
        String variableEndPoint = sendReviewText.concat("/createreview");
        return endPoint.concat(variableEndPoint);
    }


    private String getEndPointForCoupon() {

        String endPoint = "associatecoupons/";
        String variableEndPoint = coupenText.concat("/coupons");
        return endPoint.concat(variableEndPoint);
    }


    //VIEWHOLDER
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView details_text;
        private Button reviewButton, ratingButton;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            details_text = itemView.findViewById(R.id.det);
            reviewButton = itemView.findViewById(R.id.review_button);
            ratingButton = itemView.findViewById(R.id.rating_button);
        }
    }


}

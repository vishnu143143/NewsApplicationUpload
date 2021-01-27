package com.example.webproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.webproject.Default.RetrofitClient;
import com.example.webproject.Models.Adapters.ArrayAdapter;
import com.example.webproject.Models.Adapters.ReviewDisplayAdapter;
import com.example.webproject.Models.ItemsModelClass.Product;
import com.example.webproject.Models.ReviewDisplay.Review;
import com.example.webproject.Models.ReviewDisplay.ReviewData;
import com.example.webproject.Models.ReviewDisplay.ReviewDetails;
import com.example.webproject.R;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewDisplay extends AppCompatActivity {
    String url;

    ArrayList<ReviewDetails> product=new ArrayList<>();
    private ReviewDisplayAdapter arrayAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_display);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        url = getIntent().getStringExtra("ratingUrl");
        Call<Review> call = RetrofitClient.getInstance().getApi().getReview(url);
        call.enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                product = response.body().getData().getReview();

                //   recyclerView.setLayoutManager(new LinearLayoutManager(ReviewDisplay.this));

                arrayAdapter = new ReviewDisplayAdapter(product, ReviewDisplay.this);
                recyclerView.setAdapter(arrayAdapter);
                Toast.makeText(ReviewDisplay.this, "Success", Toast.LENGTH_LONG).show();
            }


            @Override
            public void onFailure(Call<Review> call, Throwable t) {
                Toast.makeText(ReviewDisplay.this, t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
}

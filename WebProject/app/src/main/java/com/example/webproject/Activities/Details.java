package com.example.webproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.webproject.Default.RetrofitClient;
import com.example.webproject.Models.Adapters.ArrayAdapter;

import com.example.webproject.Models.ItemsModelClass.Product;
import com.example.webproject.Models.ItemsModelClass.ProductDetails;
import com.example.webproject.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details extends AppCompatActivity {
    ArrayList<ProductDetails> product;
    private ArrayAdapter arrayAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        recyclerView = findViewById(R.id.recyclerview);
        // tv=findViewById(R.id.textView20);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Call<Product> call = RetrofitClient.getInstance().getApi().getData();
        call.enqueue(new Callback<Product>() {

            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                product = response.body().getData().getProduct();

                recyclerView.setLayoutManager(new LinearLayoutManager(Details.this));

                arrayAdapter = new ArrayAdapter(Details.this, product);
                recyclerView.setAdapter(arrayAdapter);
                Toast.makeText(Details.this, "Success", Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(Details.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });


    }
}

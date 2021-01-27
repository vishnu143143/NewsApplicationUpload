package com.example.webproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.webproject.R;

public class CostActivity extends AppCompatActivity {
    int dPrice,oPrice;
TextView discountPrice;
TextView originalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost);

        discountPrice=findViewById(R.id.discount_price);
        originalPrice=findViewById(R.id.original_price);
        dPrice= getIntent().getIntExtra("discount",0);
        oPrice= getIntent().getIntExtra("original",0);
        discountPrice.setText(String.valueOf(dPrice));
        originalPrice.setText(String.valueOf(oPrice));
    }
}

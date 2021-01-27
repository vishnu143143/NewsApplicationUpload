package com.example.webproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webproject.Apipackage.InterfacePassing;
import com.example.webproject.Default.RetrofitClient;
import com.example.webproject.Models.PriceDisplayingClass.Cost;
import com.example.webproject.Models.Adapters.CouponArrayAdapter;
import com.example.webproject.Models.CouponDisplayingModelClass.CouponProduct;
import com.example.webproject.Models.CouponDisplayingModelClass.CouponProductDetails;
import com.example.webproject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Coupons extends AppCompatActivity implements InterfacePassing, View.OnClickListener {
    private RecyclerView recyclerView;
    int original, discount;
    String title;
    int ippp = -1;
    String url,idString;
    Cost cost;
    int productId;
    Button submitButton;
    TextView productName;
    ArrayList<CouponProductDetails> product;

    CouponArrayAdapter couponArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);
        submitButton = findViewById(R.id.submit);
        recyclerView = findViewById(R.id.recycler_view);
        productName = findViewById(R.id.product_name);
        // tv=findViewById(R.id.textView20);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        productId = getIntent().getIntExtra("id", 0);

//        idString=getIntent().getStringExtra("id");
//        productId=Integer.parseInt(idString);
        productName.setText(title);

        Call<CouponProduct> call = RetrofitClient.getInstance().getApi().getCouponData(url);
        call.enqueue(new Callback<CouponProduct>() {
            @Override
            public void onResponse(Call<CouponProduct> call, Response<CouponProduct> response) {
                product = response.body().getData().getProduct();

                recyclerView.setLayoutManager(new LinearLayoutManager(Coupons.this));

                couponArrayAdapter = new CouponArrayAdapter(Coupons.this, product, Coupons.this);
                recyclerView.setAdapter(couponArrayAdapter);


            }

            @Override
            public void onFailure(Call<CouponProduct> call, Throwable t) {
                Toast.makeText(Coupons.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        submitButton.setOnClickListener(this);
    }

    @Override
    public void pass(int position) {

        ippp = position;
        ippp = ippp;

        //  Log.e("position",String.valueOf(position));
    }

    @Override
    public void onClick(View view) {

        if (ippp != -1) {

            Map<String, Object> map = new HashMap<>();


            map.put("product_id", productId);
            map.put("promo_id", ippp);

            Call<Cost> call = RetrofitClient.getInstance().getApi().postingId(map);
            call.enqueue(new Callback<Cost>() {
                @Override
                public void onResponse(Call<Cost> call, Response<Cost> response) {
                    discount = response.body().getData().getAmount();
                    original = response.body().getData().getOriginalprice();


                    Intent ii = new Intent(Coupons.this, CostActivity.class);
                    ii.putExtra("discount", discount);
                    ii.putExtra("original", original);
                    startActivity(ii);

                }

                @Override
                public void onFailure(Call<Cost> call, Throwable t) {
                    Toast.makeText(Coupons.this, t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });
//    call.enqueue(new Callback<Cost>() {
//        @Override
//        public void onResponse(Call<Cost> call, Response<Cost> response) {
//
//            try {
//            cost=response.body().getData
//
////
////                String s = response.body().toString();
////                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
//
//
//                Intent ii=new Intent(Coupons.this,CostActivity.class);
//                startActivity(ii);
//
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        @Override
//        public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            t.printStackTrace();
//            Toast.makeText(Coupons.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//
//        }
//    });

        } else {
            Toast.makeText(Coupons.this, "invalid", Toast.LENGTH_LONG).show();
        }
    }


}

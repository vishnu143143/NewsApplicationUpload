package com.example.webproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webproject.Default.RetrofitClient;
import com.example.webproject.R;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {
    TextView reviewUrl;
    EditText reviewName, reviewDescription, reviewRating, reviewProductId;
    String url;
    int reviewGetId;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        //   reviewUrl=findViewById(R.id.url);
        url = getIntent().getStringExtra("reviewUrl");
        reviewGetId = getIntent().getIntExtra("id", 0);

        reviewName = findViewById(R.id.reviewName);
        reviewDescription = findViewById(R.id.reviewDescription);
        reviewRating = findViewById(R.id.reviewRating);
        reviewProductId = findViewById(R.id.reviewProductId);


        b1 = findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> map = new HashMap<>();


                map.put("review[name]", reviewName.getText().toString());
                map.put("review[description]", reviewDescription.getText().toString());
                map.put("review[rating]", reviewRating.getText().toString());
                map.put("review[product_id]", reviewProductId.getText().toString());

                Call<ResponseBody> call = RetrofitClient.getInstance().getApi().hittingRating(url, map);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                        try {

                            String s = response.body().toString();
                            Toast.makeText(getApplicationContext(), "Succss", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }
        });


    }
}

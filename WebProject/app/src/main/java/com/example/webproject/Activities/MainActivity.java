package com.example.webproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webproject.Models.SigninModelClass.LoginResponse;
import com.example.webproject.R;
import com.example.webproject.Default.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{
    EditText gmailText, passKey;
    Button loginButton;
    String gmail, pass;
    TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gmailText = findViewById(R.id.gmailText);
        registerText=findViewById(R.id.register);
      //  gmailText.addTextChangedListener(this);
        passKey = findViewById(R.id.pass);
        loginButton = findViewById(R.id.login);
        gmail = gmailText.getText().toString();
        pass = passKey.getText().toString();

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Register.class);
                startActivity(i);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<LoginResponse> call = RetrofitClient.getInstance().getApi().userSignin(gmailText.getText().toString(),  passKey.getText().toString());
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        LoginResponse loginResponse = response.body();

                            if (!loginResponse.isIs_success()) {
                               Intent i=new Intent(MainActivity.this,Details.class);
                               startActivity(i);

                            }
                            else{
                                Toast.makeText(MainActivity.this,loginResponse.getMessages(),Toast.LENGTH_LONG).show();
                            }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }



}
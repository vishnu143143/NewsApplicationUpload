package com.example.webproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webproject.R;
import com.example.webproject.Default.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    // JsonPlaceHolderApi jsonPlaceHolderApi;
    Button registerButton;
    TextView loginText;
    EditText gmailText, passwordOne, passwordTwo;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton = findViewById(R.id.register_button);
        gmailText = findViewById(R.id.gmail_text);
        passwordOne = findViewById(R.id.password_one);
        passwordTwo = findViewById(R.id.password_two);
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Register.this,MainActivity.class);
                startActivity(i);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                validateMethod();
            }
        });
    }


    public void validateMethod() {
        String gmail = gmailText.getText().toString().trim();
        String password = passwordOne.getText().toString().trim();
        String passwordSecond = passwordTwo.getText().toString().trim();
        if (gmail.isEmpty()) {
            gmailText.setError("Email is required");
            gmailText.requestFocus();
            return;
        }
        if (!gmail.matches(emailPattern)) {
            gmailText.setError("Email a valid Email");
            gmailText.requestFocus();
            return;
        }
        if (password.isEmpty() || password.length() < 6) {
            passwordOne.setError("minimum six digit Password is required ");
            passwordOne.requestFocus();
            return;
        }
        if (passwordSecond.isEmpty() || passwordSecond.length() < 6) {
            passwordTwo.setError("Conform Password Required ");
            passwordTwo.requestFocus();
            return;
        }


        if (password.equals(passwordSecond)) {
            Map<String, Object> map = new HashMap<>();


            map.put("user[email]", gmail);
            map.put("user[password]", password);
            map.put("user[password_confirmation]", passwordSecond);


            Call<ResponseBody> call = RetrofitClient.getInstance().getApi().signUp(map);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    try {

                        String s = response.body().toString();
                        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            passwordTwo.setError("password does not matched");
            passwordTwo.requestFocus();
            return;

        }
    }


}


    //    https://maps.googleapis.com//api/v1/fb_login
//https://reqres.in/api/users
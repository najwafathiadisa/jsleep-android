package com.NajwaFathiadisaJSleepMN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

import android.view.View;
import android.widget.*;


import com.NajwaFathiadisaJSleepMN.jsleep_android.model.Account;
import com.NajwaFathiadisaJSleepMN.jsleep_android.request.BaseApiService;
import com.NajwaFathiadisaJSleepMN.jsleep_android.request.UtilsApi;

/**
 * Login Activity allows user to login the app
 * by entering email and password
 * @author Najwa Fathiadisa
 */
public class LoginActivity extends AppCompatActivity {
    BaseApiService mApiService;
    EditText username, password;
    Context mContext;

    /**
     * @param savedInstanceState --> to initialize the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        username = findViewById(R.id.fillEmail_Login);
        password = findViewById(R.id.fillPadd_Login);

        TextView register = findViewById(R.id.regisNow);
        Button login = findViewById(R.id.loginButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(move);
                Toast toast = Toast.makeText(getApplicationContext(), "Register Page", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account loginAccount = requestLogin();
                // Account account = requestAccount();
                //Intent move = new Intent(LoginActivity.this, MainActivity.class);
                //startActivity(move);
            }
        });
    }

    protected Account requestAccount(){
        mApiService.getAccount(0).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    Account account;
                    account = response.body();
                    System.out.println(account.toString());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t){

                System.out.println(t.toString());
                Toast.makeText(mContext, "no Account id=0", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

    protected Account requestLogin(){
        mApiService.loginRequest(username.getText().toString(), password.getText().toString()).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    MainActivity.loginAccount = response.body();
                    Toast.makeText(mContext, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent move = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(move);
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println(t.toString());
                Toast.makeText(mContext, "Failed!", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}
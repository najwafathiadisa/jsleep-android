package com.NajwaFathiadisaJSleepMN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.NajwaFathiadisaJSleepMN.jsleep_android.model.Account;
import com.NajwaFathiadisaJSleepMN.jsleep_android.model.Renter;
import com.NajwaFathiadisaJSleepMN.jsleep_android.request.BaseApiService;
import com.NajwaFathiadisaJSleepMN.jsleep_android.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

/**
 * About me activity shows the details of the Account
 * containing name, email, balance, address, phone number,
 * and also top up feature
 * @author Najwa Fathiadisa
 */
public class AboutMeActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Handler mHandler;
    TextView name, email, balance, data_username, data_address, data_phone;
    Button buttonRenter, buttonRegist, buttonCancel,topUp;
    Context mContext;
    LinearLayout renter_button_lay, regist_lay, data_lay;
    EditText username, address, phone_number,amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mApiService = UtilsApi.getApiService();
        mContext = this;

        setContentView(R.layout.activity_about_me);

        renter_button_lay = (LinearLayout) findViewById(R.id.registRenter_buttonLayout);
        regist_lay = (LinearLayout) findViewById(R.id.registRenter_layout);
        data_lay = (LinearLayout) findViewById(R.id.sec_layout);
        buttonRenter = findViewById(R.id.button_registRenter);
        buttonRegist = findViewById(R.id.registRenterButton);

        name = findViewById(R.id.fillName_AboutMe);
        email = findViewById(R.id.fillEmail);
        balance = findViewById(R.id.Balance_Input);

        buttonCancel = findViewById(R.id.renterCancelButton);
        data_username = findViewById(R.id.renter_name_data);
        data_address = findViewById(R.id.addressData);
        data_phone = findViewById(R.id.phoneData);
        username = findViewById(R.id.renterNameAbtMe);
        address = findViewById(R.id.addrssRenterAbtMe);
        phone_number = findViewById(R.id.phoneRenterAbtMe);
        topUp = findViewById(R.id.topupButton);

        balance.setText(String.valueOf(MainActivity.loginAccount.balance));
        name.setText(MainActivity.loginAccount.name);
        email.setText(MainActivity.loginAccount.email);
        amount = findViewById(R.id.Amount);

        topUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopUp(MainActivity.loginAccount.id,Double.parseDouble(amount.getText().toString()));
            }
        });

        if (MainActivity.loginAccount.renter == null) {
            renter_button_lay.setVisibility(LinearLayout.VISIBLE);
            regist_lay.setVisibility(LinearLayout.INVISIBLE);
            data_lay.setVisibility(LinearLayout.INVISIBLE);
            buttonRenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    regist_lay.setVisibility(LinearLayout.VISIBLE);
                    data_lay.setVisibility(LinearLayout.INVISIBLE);
                    renter_button_lay.setVisibility(LinearLayout.INVISIBLE);

                    buttonRegist.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("clicked");
                            Account registerRenterAccount = requestRenter();
                            recreate();
                        }
                    });
                    buttonCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            regist_lay.setVisibility(LinearLayout.INVISIBLE);
                            data_lay.setVisibility(LinearLayout.INVISIBLE);
                            renter_button_lay.setVisibility(LinearLayout.VISIBLE);
                        }
                    });
                }
            });
        } if (MainActivity.loginAccount.renter != null) {
            data_username.setText(MainActivity.loginAccount.renter.username);
            data_address.setText(MainActivity.loginAccount.renter.address);
            data_phone.setText(String.valueOf(MainActivity.loginAccount.renter.phoneNumber));
            regist_lay.setVisibility(LinearLayout.INVISIBLE);
            renter_button_lay.setVisibility(LinearLayout.INVISIBLE);
            data_lay.setVisibility(LinearLayout.VISIBLE);
        }
    }

    protected Renter TopUp(int id, double balance){
        mApiService.topUp(id,balance).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()){
                    Toast.makeText(mContext, "Top Up Success", Toast.LENGTH_SHORT).show();
                    MainActivity.loginAccount.balance += balance;
                    recreate();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(mContext, "Top Up Fail", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

    protected Account requestRenter() {
        System.out.println("req");
        mApiService.registerRenterRequest (
                MainActivity.loginAccount.id,
                username.getText().toString(),
                address.getText().toString(),
                phone_number.getText().toString()
        ).enqueue(new Callback<Renter>() {
            @Override
            public void onResponse(Call<Renter> call, Response<Renter> response) {
                if(response.isSuccessful()){
                    MainActivity.loginAccount.renter = response.body();
                    Toast.makeText(mContext, "Register Renter Success!", Toast.LENGTH_SHORT).show();

                    data_username.setText(MainActivity.loginAccount.renter.username);
                    data_address.setText(MainActivity.loginAccount.renter.address);
                    data_phone.setText(String.valueOf(MainActivity.loginAccount.renter.phoneNumber));
                    regist_lay.setVisibility(LinearLayout.INVISIBLE);
                    renter_button_lay.setVisibility(LinearLayout.INVISIBLE);
                    data_lay.setVisibility(LinearLayout.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<Renter> call, Throwable t) {
                System.out.println(t.toString());
                Toast.makeText(mContext, "Register Renter Failed!", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}
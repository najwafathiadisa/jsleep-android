package com.NajwaFathiadisaJSleepMN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.NajwaFathiadisaJSleepMN.jsleep_android.model.Account;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        Account account = MainActivity.accountObject;
        TextView name = findViewById(R.id.Namanya);
        TextView email = findViewById(R.id.Emailnya);
        TextView balance = findViewById(R.id.Balancenya);
        name.setText(account.name);
        email.setText(account.email);
        balance.setText(Double.toString(account.balance));

    }
}
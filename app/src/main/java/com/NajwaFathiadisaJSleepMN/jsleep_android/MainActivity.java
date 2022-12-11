package com.NajwaFathiadisaJSleepMN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.NajwaFathiadisaJSleepMN.jsleep_android.model.Account;
import com.NajwaFathiadisaJSleepMN.jsleep_android.model.Room;
import com.NajwaFathiadisaJSleepMN.jsleep_android.request.BaseApiService;
import com.NajwaFathiadisaJSleepMN.jsleep_android.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * main activity shows after the login screen
 * showing rooms and also some buttons
 * @author Najwa Fathiadisa
 */
public class MainActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;
    public static List<Room> getRoom;
    public static Account loginAccount;
    public static int indextemp;
    Button previous, next, go;
    ListView data;
    EditText page;
    int pagedisplay;

    /**
     * a method to initialize the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pagedisplay = 0;
        System.out.println(loginAccount.id);
        mContext = this;
        mApiService = UtilsApi.getApiService();

        previous = findViewById(R.id.previousButton);
        next = findViewById(R.id.nextButton);
        page = findViewById(R.id.pageCount);
        data = findViewById(R.id.list_View);
        go = findViewById(R.id.goButton);

        data.setOnItemClickListener(this::onItemClick);

        getAllRoom(pagedisplay);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagedisplay++;
                getAllRoom(pagedisplay);
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagedisplay--;
                getAllRoom(pagedisplay);
            }
        });
    }

    protected void getAllRoom(int page) {
        System.out.println("masuk");
        mApiService.getAllRoom (
                page,10
        ).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if(response.isSuccessful()){
                    List<Room> listroom = response.body();
                    System.out.println(listroom);
                    getRoom = (ArrayList<Room>) response.body();
                    ArrayList<String> list = new ArrayList<>();
                    assert getRoom != null;
                    for (Room room : getRoom){
                        list.add(room.name);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext,android.R.layout.simple_list_item_1,list);
                    data.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Room>> call, Throwable x) {
                System.out.println(x.toString());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.person_button) {
            Intent inten = new Intent(MainActivity.this,AboutMeActivity.class);
            startActivity(inten);
            Toast toast = Toast.makeText(getApplicationContext(), "Account Details", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (item.getItemId() == R.id.add_button) {
            Intent inten2 = new Intent(MainActivity.this, CreateRoom.class);
            startActivity(inten2);
            Toast toast2 = Toast.makeText(getApplicationContext(), "Account Details", Toast.LENGTH_SHORT);
            toast2.show();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.topmenu, menu);
        return true;
    }

    public void onItemClick (AdapterView<?> l, View v, int position, long id){
        Intent intent = new Intent();
        indextemp = position;
        intent.setClass(mContext, DetailRoom.class);
        intent.putExtra("position", position);
        intent.putExtra("id", id);
        startActivity(intent);
    }

}
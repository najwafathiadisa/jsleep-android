package com.NajwaFathiadisaJSleepMN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import com.NajwaFathiadisaJSleepMN.jsleep_android.model.BedType;
import com.NajwaFathiadisaJSleepMN.jsleep_android.model.City;
import com.NajwaFathiadisaJSleepMN.jsleep_android.model.Facility;
import com.NajwaFathiadisaJSleepMN.jsleep_android.model.Room;
import com.NajwaFathiadisaJSleepMN.jsleep_android.request.BaseApiService;
import com.NajwaFathiadisaJSleepMN.jsleep_android.request.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * CreateRoom contains inputs of
 * name , address, size, bed type, facility, and city
 * @author Najwa Fathiadisa
 */
public class CreateRoom extends AppCompatActivity {
    Context mContext;
    BaseApiService mApiService;
    Button btnCreateRoom;
    EditText etName, etSize, etPrice, etAddress;
    ArrayList<Facility> facility = new ArrayList<Facility>();
    CheckBox fridge, wifi, bathub, balcony, restaurant, pool, fitness,ac;
    Spinner Spinnercity, Spinnerbedtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        Spinnercity = findViewById(R.id.Spinner_city);
        Spinnerbedtype = findViewById(R.id.Spinner_bed);
        Spinnerbedtype.setAdapter(new ArrayAdapter<BedType>(this, android.R.layout.simple_spinner_item, BedType.values()));
        Spinnercity.setAdapter(new ArrayAdapter<City>(this, android.R.layout.simple_spinner_item, City.values()));
        btnCreateRoom = findViewById(R.id.createButton);

        ac = findViewById(R.id.cekAC);
        fridge = findViewById(R.id.cekKulkas);
        wifi = findViewById(R.id.cekWifi);
        bathub = findViewById(R.id.cekBathub);
        balcony = findViewById(R.id.cekBalkon);
        restaurant = findViewById(R.id.cekResto);
        pool = findViewById(R.id.cekSwim);
        fitness = findViewById(R.id.cekFitness);

        etName = findViewById(R.id.renterNameAbtMe);
        etSize = findViewById(R.id.inputSizeRoom);
        etPrice = findViewById(R.id.inputPriceRoom);
        etAddress = findViewById(R.id.inputAddress);

        String bed = Spinnerbedtype.getSelectedItem().toString();
        String cityStr = Spinnercity.getSelectedItem().toString();
        BedType bedType = BedType.valueOf(bed);
        City city = City.valueOf(cityStr);

        btnCreateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ac.isChecked()) {
                    facility.add(Facility.AC);
                }
                if (fridge.isChecked()) {
                    facility.add(Facility.Refrigerator);
                }
                if (wifi.isChecked()) {
                    facility.add(Facility.Wifi);
                }
                if (bathub.isChecked()) {
                    facility.add(Facility.Bathtub);
                }
                if (balcony.isChecked()) {
                    facility.add(Facility.Balcony);
                }
                if (restaurant.isChecked()) {
                    facility.add(Facility.Restaurant);
                }
                if (pool.isChecked()) {
                    facility.add(Facility.SwimmingPool);
                }
                if (fitness.isChecked()) {
                    facility.add(Facility.FitnessCenter);
                }
                String bed = Spinnerbedtype.getSelectedItem().toString();
                String cityStr = Spinnercity.getSelectedItem().toString();

                BedType bedType = BedType.valueOf(bed);
                City city = City.valueOf(cityStr);

                int size = Integer.parseInt(etSize.getText().toString());
                int price = Integer.parseInt(etPrice.getText().toString());

                requestRoom(MainActivity.loginAccount.id, etName.getText().toString(), size,price, facility, city, etAddress.getText().toString(), bedType);
                Toast toast = Toast.makeText(getApplicationContext(), "Register Page", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    /**
     * Room is a method that can creates a new room
     * @param id --> id of renter
     * @param name --> the room name
     * @param size --> size of the room
     * @param price --> price of room
     * @param facility --> types of the room's facility
     * @param city --> city of the room
     * @param address --> address of the room
     * @param bedType --> type of the bed
     * @return
     */
    protected Room requestRoom(int id, String name, int size, int price, ArrayList<Facility> facility, City city, String address, BedType bedType) {
        System.out.println("masuk");
        mApiService.createRoom(id,name,size,price,facility,city,address,bedType).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if (response.isSuccessful()) {
                    System.out.println("Request Berhasil");
                    Room responseq = response.body();
                    Intent move = new Intent(CreateRoom.this, MainActivity.class);
                    startActivity(move);
                    System.out.println(responseq.toString());

                }
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                Toast.makeText(mContext, "Fail To Create Room", Toast.LENGTH_SHORT).show();
                //Intent move = new Intent(CreateRoom.this, MainActivity.class);
               // startActivity(move);
            }
        });
        return null;
    }
}
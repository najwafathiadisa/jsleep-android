package com.NajwaFathiadisaJSleepMN.jsleep_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.NajwaFathiadisaJSleepMN.jsleep_android.model.Facility;
import com.NajwaFathiadisaJSleepMN.jsleep_android.model.Room;

public class DetailRoom extends AppCompatActivity {
    /**
     * DetailRoom can be clicked to show more information about
     * the room that already created
     * @author najwa Fathiadisa
     */
    Room temproom;
    TextView name, size, price, address, bedtype, city;
    CheckBox fridge, wifi, bathub, balcony, restaurant, pool, fitness,ac;

    /**
     * @param savedInstanceState  --> to initialize the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_room);
        temproom = MainActivity.getRoom.get(MainActivity.indextemp);
        name = findViewById(R.id.fillName);
        size = findViewById(R.id.SizeFillText);
        price = findViewById(R.id.PriceFillText);
        address = findViewById(R.id.RoomAddressFillText);
        bedtype = findViewById(R.id.fillBedType);
        city = findViewById(R.id.CityFillText);
        ac = findViewById(R.id.checkboxDetailac);
        fridge = findViewById(R.id.checkboxDetailrefrigerator);
        wifi = findViewById(R.id.checkboxDetailwifi);
        bathub = findViewById(R.id.checkboxDetailbathub);
        balcony = findViewById(R.id.cekBalkonDetail);
        restaurant = findViewById(R.id.cekRestoDetail);
        pool = findViewById(R.id.cekswimDeteail);
        fitness = findViewById(R.id.cekfitnessDetail);
        name.setText(temproom.name);
        size.setText(String.valueOf(temproom.size));
        price.setText(String.valueOf(temproom.price.price));
        address.setText(temproom.address);
        bedtype.setText(temproom.bedType.toString());
        city.setText(temproom.city.toString());

        if (temproom.facility.contains(Facility.AC)) {
            ac.setChecked(true);
        }
        if (temproom.facility.contains(Facility.Refrigerator)) {
            fridge.setChecked(true);
        }
        if (temproom.facility.contains(Facility.Wifi)) {
            wifi.setChecked(true);
        }
        if (temproom.facility.contains(Facility.Bathtub)) {
            bathub.setChecked(true);
        }
        if (temproom.facility.contains(Facility.Balcony)) {
            balcony.setChecked(true);
        }
        if (temproom.facility.contains(Facility.Restaurant)) {
            restaurant.setChecked(true);
        }
        if (temproom.facility.contains(Facility.SwimmingPool)) {
            pool.setChecked(true);
        }
        if (temproom.facility.contains(Facility.FitnessCenter)) {
            fitness.setChecked(true);
        }

    }
}
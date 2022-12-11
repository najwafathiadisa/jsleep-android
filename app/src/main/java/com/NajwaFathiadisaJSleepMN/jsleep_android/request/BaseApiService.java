package com.NajwaFathiadisaJSleepMN.jsleep_android.request;

import com.NajwaFathiadisaJSleepMN.jsleep_android.model.Account;
import com.NajwaFathiadisaJSleepMN.jsleep_android.model.BedType;
import com.NajwaFathiadisaJSleepMN.jsleep_android.model.City;
import com.NajwaFathiadisaJSleepMN.jsleep_android.model.Facility;
import com.NajwaFathiadisaJSleepMN.jsleep_android.model.Renter;
import com.NajwaFathiadisaJSleepMN.jsleep_android.model.Room;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {
    @GET("room/getAllRoom")
    Call<List<Room>> getAllRoom (
            @Query("page") int page, @Query("pageSize") int pageSize
    );

    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);

    @POST("/account/login")
    Call<Account> loginRequest(
            @Query("email") String email, @Query("password") String password
    );

    @POST("/account/{id}/registerRenter")
    Call<Renter> registerRenterRequest ( @Path("id") int id,
                                         @Query("name") String username, @Query("address")  String address,
                                         @Query("phoneNumber") String phoneNumber
    );

    @POST("/account/register")
    Call<Account> registerRequest(
            @Query("name") String name, @Query("email") String email,
            @Query("password") String password
    );

    @POST("account/{id}/topUp")
    Call<Boolean> topUp (@Path("id") int id, @Query("balance") double balance);

    @POST("/room/create")
    Call<Room> createRoom(
            @Query("accountId") int accountId,
            @Query("name") String name,
            @Query("size") int size,
            @Query("price") int price,
            @Query("facility") ArrayList<Facility> facility,
            @Query("city") City city,
            @Query("address") String address,
            @Query("bedType")BedType bedType
    );
}

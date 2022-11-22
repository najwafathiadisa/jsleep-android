package request;

import android.accounts.Account;

import model.Room;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {
    @GET("account/{id}")
    Call<Account> getAccount (@Path("id")int id);

    @POST("account/login")
    Call<Account> login (@Query("email") String email, @Query("password") String Password);

    @GET("room/{id}")
    Call<Room> getRoom (@Path("id") int id);

    @GET("account/register")
    Call<Account> register(@Query("username") String username, @Query("email") String email , @Query("password") String password);



}

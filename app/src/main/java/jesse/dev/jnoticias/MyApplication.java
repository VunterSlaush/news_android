package jesse.dev.jnoticias;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Slaush on 08/10/2017.
 */

public class MyApplication extends Application
{
    private static MyApplication instance;
    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {

        return instance;
    }

    public Retrofit getRetrofit()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jnoticias.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}

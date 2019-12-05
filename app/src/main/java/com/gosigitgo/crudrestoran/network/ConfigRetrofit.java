package com.gosigitgo.crudrestoran.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit{
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.60.75/server_resto_ios/index.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiService service = retrofit.create(ApiService.class);
}

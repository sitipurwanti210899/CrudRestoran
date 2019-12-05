package com.gosigitgo.crudrestoran.network;

import com.gosigitgo.crudrestoran.model.ResponseGetMakanan;
import com.gosigitgo.crudrestoran.model.ResponseLogin;
import com.gosigitgo.crudrestoran.model.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    //buat transaksi login
    @FormUrlEncoded
    @POST("Api/Login")
    Call<ResponseLogin> actionLogin(@Field("email") String email,
                                    @Field("password") String password);

    //buat transaksi register
    @FormUrlEncoded
    @POST("Api/Register")
    Call<ResponseRegister> actionRegister (@Field("name") String name,
                                           @Field("email") String email,
                                           @Field("password") String password,
                                            @Field("hp") String hp);

    //getmakanan
    @GET("Api/getMakanan")
    Call<ResponseGetMakanan> getAllMakanan();

    @FormUrlEncoded
    @POST("Api/insertMakanan")
    Call<ResponseGetMakanan> insertMakanan (@Field("name") String name,
                                            @Field("price") String price,
                                            @Field("gambar") String gambar);
    //delete
    @FormUrlEncoded
    @POST("Api/deleteMakanan")
    Call<ResponseGetMakanan> deleteMakanan (@Field("id") String name);

    //update
    @FormUrlEncoded
    @POST("Api/updateMakanan")
    Call<ResponseGetMakanan> updateMakanan (@Field("id") String id,
                                            @Field("name") String name,
                                            @Field("price") String price,
                                            @Field("gambar") String gambar);
}

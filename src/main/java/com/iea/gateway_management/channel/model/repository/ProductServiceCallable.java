package com.iea.gateway_management.channel.model.repository;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

// ***14 -> ProductService
public interface ProductServiceCallable
{
    @GET("api/product")
    Call<List<JsonElement>> getAll();

    @DELETE("api/product/{productID}")
    Call<Void> deleteByID(@Path("productID") Integer productID);

    @POST("api/product")
    Call<JsonElement> save(JsonElement requestBody);
}

package com.iea.gateway_management.channel.model.repository;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

// ***16 -> AbstractTransactionService
public interface TransactionServiceCallable
{
    @GET("api/transaction/{userID}")
    Call<List<JsonElement>> getAllTransactionsOfAuthorizedUser(@Path("userID") Integer userID);

    @DELETE("api/transaction/{transactionID}")
    Call<Void> deleteByID(@Path("transactionID") Integer transactionID);

    @POST("api/transaction")
    Call<JsonElement> save(JsonElement requestBody);
}

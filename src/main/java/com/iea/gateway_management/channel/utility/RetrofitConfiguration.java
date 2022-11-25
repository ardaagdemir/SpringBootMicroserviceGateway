package com.iea.gateway_management.channel.utility;

import com.google.gson.Gson;
import com.iea.gateway_management.channel.model.repository.ProductServiceCallable;
import com.iea.gateway_management.channel.model.repository.TransactionServiceCallable;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfiguration
{
    @Value("${retrofit.timeout}")
    private long TIMEOUT_IN_SECONDS;

    @Bean
    public TransactionServiceCallable createTransactionServiceRequest(Retrofit.Builder secureKeyBuilder,
                                                                  @Value("${transaction.service.url}") String baseUrl){
        return secureKeyBuilder.baseUrl(baseUrl).build().create(TransactionServiceCallable.class);
    }


    //
    @Bean
    public ProductServiceCallable createProductServiceRequest(Retrofit.Builder secureKeyBuilder,
                                                              @Value("${product.service.url}") String baseUrl){
        return secureKeyBuilder.baseUrl(baseUrl).build().create(ProductServiceCallable.class);
    }

    // secureKeyClient'ı kullanarak, Retrofit.Builder üretildi.
    @Bean
    public Retrofit.Builder secureKeyBuilder(OkHttpClient secureKeyClient, Gson gson)
    {
        return new Retrofit.Builder().client(secureKeyClient).addConverterFactory(GsonConverterFactory.create(gson));
    }

    // (OKHttpClient.Builder döndüren createDefaultClientBuilder() üzerinden güvenli bir OkHttpClient üretildi.)
    @Bean
    public OkHttpClient secureKeyClient( @Value("${service.security.secure-key-username}") String secureKeyUsername,
                                         @Value("${service.security.secure-key-password}")String secureKeyPassword)
    {
        // Burada, OkHttpClient.Builder nesnesi döndüren bir metot üzerinden gidilecek.
        return createDefaultClientBuilder().addInterceptor(interceptor -> interceptor.proceed(interceptor.request().newBuilder()
                                           .header("Authorization", Credentials.basic(secureKeyUsername, secureKeyPassword))
                                           .build())).build();
    }

    // varsayılan OKHttpClient.Builder
    private OkHttpClient.Builder createDefaultClientBuilder()
    {
         return new OkHttpClient.Builder()
                 .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                 .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                 .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
    }
}

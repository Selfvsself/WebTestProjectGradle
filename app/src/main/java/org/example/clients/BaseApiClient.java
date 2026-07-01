package org.example.clients;

import io.qameta.allure.okhttp3.AllureOkHttp3;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class BaseApiClient {
    private static final OkHttpClient COMMON_HTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(new AllureOkHttp3())
            .build();

    protected <S> S createClient(Class<S> serviceClass, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(COMMON_HTTP_CLIENT)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(serviceClass);
    }

    public <T> T executeCall(Call<T> call) {
        try {
            Response<T> response = call.execute();

            if (!response.isSuccessful()) {
                try (okhttp3.ResponseBody errorBody = response.errorBody()) {
                    String errorContent = (errorBody != null) ? errorBody.string() : "No error body";

                    throw new AssertionError(String.format(
                            "API Error! URL: %s, Response code: %d, Message: %s",
                            call.request().url(), response.code(), errorContent
                    ));
                } catch (IOException e) {
                    throw new AssertionError(String.format(
                            "API Error! URL: %s, Response code: %d (Failed to read error body)",
                            call.request().url(), response.code()
                    ), e);
                }
            }

            return response.body();

        } catch (IOException e) {
            throw new RuntimeException("Error sending HTTP request to: " + call.request().url(), e);
        }
    }
}

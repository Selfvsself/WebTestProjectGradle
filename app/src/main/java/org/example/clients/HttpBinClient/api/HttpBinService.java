package org.example.clients.HttpBinClient.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface HttpBinService {

    @POST("delay/{delay}")
    Call<Object> postDelay(
            @Path("delay") long delay
    );

    @Streaming
    @GET("image/png")
    Call<ResponseBody> getPNGImage();
}

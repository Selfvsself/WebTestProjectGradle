package org.example.clients.HttpBinClient;

import io.qameta.allure.Step;
import okhttp3.ResponseBody;
import org.example.clients.BaseApiClient;
import org.example.clients.HttpBinClient.api.HttpBinService;
import retrofit2.Response;

public class HttpBinClient extends BaseApiClient {

    private final HttpBinService httpBinClient = createClient(
            HttpBinService.class,
            System.getProperty("http_bin_url", "https://httpbin.io/")
    );

    @Step("Send an API request to delay with value: '{value}'")
    public long postDelayAndGetTime(long value) {
        return executeAndMeasureTime(httpBinClient.postDelay(value));
    }

    @Step("Send an API request to retrieve a PNG image.")
    public Response<ResponseBody> downloadPNGImage() {
        return execute(httpBinClient.getPNGImage());
    }
}

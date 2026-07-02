package org.example.tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import okhttp3.ResponseBody;
import org.example.clients.HttpBinClient.HttpBinClient;
import org.example.clients.HttpBinClient.ImageResponseAssertions;
import org.testng.annotations.Test;
import retrofit2.Response;

@Epic("Backend Services")
@Feature("Uploading media files")
public class PngImageApiTest {
    private final HttpBinClient httpBinClient = new HttpBinClient();

    @Test(description = "Downloading and validating a PNG image via the API")
    @Description("The test verifies that the /image/png endpoint returns a valid binary file with the correct headers.")
    public void testDownloadPngImage() {
        Response<ResponseBody> response = httpBinClient.downloadPNGImage();

        ImageResponseAssertions.assertThat(response)
                .hasContentType("image/png")
                .hasValidSize(1000)
                .isValidPngStructure();
    }
}

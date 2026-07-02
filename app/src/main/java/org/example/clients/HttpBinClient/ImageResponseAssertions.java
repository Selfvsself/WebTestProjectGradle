package org.example.clients.HttpBinClient;

import io.qameta.allure.Step;
import okhttp3.ResponseBody;
import org.assertj.core.api.Assertions;
import retrofit2.Response;

import java.io.IOException;

public class ImageResponseAssertions {

    private final Response<ResponseBody> response;
    private final byte[] imageBytes;

    public ImageResponseAssertions(Response<ResponseBody> response) {
        this.response = response;
        try {
            this.imageBytes = response.body() != null ? response.body().bytes() : new byte[0];
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the byte array from the response.", e);
        }
    }

    public static ImageResponseAssertions assertThat(Response<ResponseBody> response) {
        return new ImageResponseAssertions(response);
    }

    @Step("Verify that the Content-Type header matches the format '{expectedType}'")
    public ImageResponseAssertions hasContentType(String expectedType) {
        String actualContentType = response.headers().get("Content-Type");
        Assertions.assertThat(actualContentType)
                .as("HTTP Header 'Content-Type'")
                .isNotNull()
                .containsIgnoringCase(expectedType);
        return this;
    }

    @Step("Ensure that the size of the downloaded file is greater than {minSizeInBytes} bytes")
    public ImageResponseAssertions hasValidSize(int minSizeInBytes) {
        Assertions.assertThat(imageBytes.length)
                .as("File size in bytes")
                .isGreaterThan(minSizeInBytes);
        return this;
    }

    @Step("Verify the validity of the file structure (PNG format magic numbers)")
    public ImageResponseAssertions isValidPngStructure() {
        byte[] expectedPngSignature = new byte[]{(byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A};

        Assertions.assertThat(imageBytes)
                .as("Бинарный массив файла")
                .startsWith(expectedPngSignature);
        return this;
    }
}

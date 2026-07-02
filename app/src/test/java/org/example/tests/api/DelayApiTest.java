package org.example.tests.api;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.clients.HttpBinClient.HttpBinClient;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Backend Services")
@Feature("Delay functionality")
public class DelayApiTest {
    private final HttpBinClient httpBinClient = new HttpBinClient();
    private static final long DELAY_SECONDS = 3;
    private static final long EXPECTED_DELAY_MS_MIN = DELAY_SECONDS * 1000L;
    private static final long EXPECTED_DELAY_MS_MAX = EXPECTED_DELAY_MS_MIN + 500L;

    @Test(description = "Verify HTTP service connection holding time")
    @Description("The test triggers a delayed response from the server and validates " +
            "that the actual connection holding time satisfies the specified expected")
    public void testSearchSuggestionApi() {
        long actualResponseTimeMs = httpBinClient.postDelayAndGetTime(DELAY_SECONDS);

        Allure.step(String.format("Verify that the actual response time (%d ms) falls within the range of %d to %d ms",
                actualResponseTimeMs, EXPECTED_DELAY_MS_MIN, EXPECTED_DELAY_MS_MAX), () -> {

            assertThat(actualResponseTimeMs)
                    .as("API delay should hold the connection for requested time")
                    .isBetween(EXPECTED_DELAY_MS_MIN, EXPECTED_DELAY_MS_MAX);
        });
    }
}

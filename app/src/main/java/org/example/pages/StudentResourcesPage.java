package org.example.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StudentResourcesPage extends BasePage<StudentResourcesPage> {

    private final SelenideElement pageAnchor = $("#textbooks");
    private final ElementsCollection headers = $$(".w-anchor-link-nav__item");

    @Override
    @Step("Wait for Resources Page loaded")
    public StudentResourcesPage waitPageLoaded() {
        pageAnchor.shouldBe(Condition.visible);
        return this;
    }

    @Step("Check that the headers are displayed on the page:  {expectedHeaders}")
    public StudentResourcesPage checkHeaders(List<String> expectedHeaders) {
        headers.shouldHave(CollectionCondition.textsInAnyOrder(expectedHeaders));
        return this;
    }
}

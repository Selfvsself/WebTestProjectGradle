package org.example.components;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

public class SubMenuComponent {

    private final ElementsCollection menuItems;
    private final SelenideElement root;

    public SubMenuComponent(SelenideElement root) {
        this.root = root;
        this.menuItems = root.$$(".w-text--size-md");
    }

    @Step("Check that the headers are displayed in the submenu:  {expectedHeaders}")
    public SubMenuComponent checkHeaders(List<String> expectedHeaders) {
        menuItems.shouldHave(CollectionCondition.textsInAnyOrder(expectedHeaders));
        return this;
    }

    @Step("Click on the submenu header with text: '{headerText}'")
    public void clickOnHeader(String headerText) {
        menuItems.findBy(Condition.matchText("(?i).*" + headerText + ".*"))
                .shouldBe(Condition.visible)
                .click();
    }
}

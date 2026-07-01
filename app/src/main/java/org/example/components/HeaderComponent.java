package org.example.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class HeaderComponent {

    private final SelenideElement subMenuElement;

    private final SelenideElement root;

    public HeaderComponent(SelenideElement root) {
        this.root = root;
        this.subMenuElement = root.$(".w-mega-menu");
    }

    @Step("Check that the header is visible")
    public HeaderComponent shouldVisible() {
        root.should(Condition.visible);
        return this;
    }

    @Step("Click on the header item with the text: '{title}'")
    public HeaderSubMenuComponent clickOnMenuItem(String title) {
        root.$(withText(title))
                .shouldBe(Condition.enabled)
                .click();
        return new HeaderSubMenuComponent(subMenuElement);
    }

}

package org.example.components;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

public class HeaderSubMenuComponent {

    private final ElementsCollection menuItems;
    private final SelenideElement root;

    public HeaderSubMenuComponent(SelenideElement root) {
        this.root = root;
        this.menuItems = root.$$(".w-text--size-md");
    }

    @Step("Check that the headers are displayed in the submenu:  {expectedHeaders}")
    public HeaderSubMenuComponent checkHeaders(List<String> expectedHeaders) {
        menuItems.shouldHave(CollectionCondition.textsInAnyOrder(expectedHeaders));
        return this;
    }
}

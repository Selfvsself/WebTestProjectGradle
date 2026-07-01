package org.example.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class SearchResultCard {
    private final SelenideElement root;
    private final SelenideElement title;

    public SearchResultCard(SelenideElement root) {
        this.root = root;
        this.title = root.$(".w-title");
    }

    @Step("")
    public SearchResultCard checkTitleContain(String value) {
        title.shouldBe(Condition.exist)
                .scrollIntoCenter()
                .shouldHave(Condition.matchText("(?i).*" + value + ".*"));
        return this;
    }
}

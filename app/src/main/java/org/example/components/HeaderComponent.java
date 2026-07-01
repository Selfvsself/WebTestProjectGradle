package org.example.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.pages.SearchResultPage;
import org.example.utils.LayoutUtils;

import static com.codeborne.selenide.Selectors.withText;

public class HeaderComponent {

    private final SelenideElement subMenuElement;
    private final SelenideElement searchInput;
    private final SelenideElement searchSuggestion;

    private final SelenideElement root;

    public HeaderComponent(SelenideElement root) {
        this.root = root;
        this.subMenuElement = root.$(".w-mega-menu");
        this.searchInput = root.$("input[placeholder='Search']");
        this.searchSuggestion = root.$(".w-g-header__search-suggestions--isVisible");
    }

    @Step("Check that the header is visible")
    public HeaderComponent shouldVisible() {
        root.should(Condition.visible);
        return this;
    }

    @Step("Click on the header item with the text: '{title}'")
    public SubMenuComponent clickOnMenuItem(String title) {
        Selenide.Wait().until(driver -> {
            root.$(withText(title))
                    .shouldBe(Condition.visible)
                    .click();
            return subMenuElement.is(Condition.visible);
        });
        return new SubMenuComponent(subMenuElement);
    }

    @Step("Input the value into the search field: '{value}'")
    public HeaderComponent inputInSearch(String value) {
        Selenide.Wait().until(webDriver -> {
            searchInput.shouldBe(Condition.visible).click();
            searchInput.clear();
            searchInput.setValue(value);
            searchInput.shouldBe(Condition.value(value));
            return searchSuggestion.shouldBe(Condition.visible);
        });
        return this;
    }

    @Step("Press the Enter key in the search field")
    public SearchResultPage pressEnterInSearch() {
        searchInput.shouldBe(Condition.visible).pressEnter();
        return Selenide.page(SearchResultPage.class);
    }

    @Step()
    public HeaderComponent verifySuggestionsAreHorizontallyAlignedWithSearch() {
        searchInput.shouldBe(Condition.visible);
        searchSuggestion.shouldBe(Condition.visible);

        boolean isCorrectLayout = LayoutUtils.isHorizontallyAligned(searchInput, searchSuggestion);

        if (!isCorrectLayout) {
            throw new AssertionError(String.format(
                    "Layout error! The suggestion panel is misaligned. Input field coordinates: %s, Suggestion coordinates: %s",
                    searchInput.getRect().toString(),
                    searchSuggestion.getRect().toString()
            ));
        }
        return this;
    }

}

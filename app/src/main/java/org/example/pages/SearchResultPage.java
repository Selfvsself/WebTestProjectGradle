package org.example.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.components.SearchResultCard;

import java.util.function.Consumer;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultPage extends BasePage<SearchResultPage> {

    private final SelenideElement searchResult = $(".w-search-results");
    private final ElementsCollection searchCards = $$(".w-product-card__wrapper");

    @Override
    @Step("Wait for the search results to appear")
    public SearchResultPage waitPageLoaded() {
        searchResult.shouldBe(Condition.visible);
        return this;
    }

    @Step("Check that exactly {expectedNumOfCards} result cards are displayed on the page")
    public SearchResultPage checkNumberOfCards(int expectedNumOfCards) {
        searchCards.shouldHave(CollectionCondition.size(expectedNumOfCards));
        return this;
    }

    @Step("Perform the action for each product card on the page")
    public SearchResultPage performForAllCards(Consumer<SearchResultCard> action) {
        searchCards.shouldHave(CollectionCondition.sizeGreaterThan(0));
        for (SelenideElement cardRoot : searchCards) {
            SearchResultCard cardComponent = new SearchResultCard(cardRoot);
            action.accept(cardComponent);
        }
        return this;
    }
}

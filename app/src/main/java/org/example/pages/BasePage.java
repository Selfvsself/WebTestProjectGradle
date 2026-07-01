package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.example.components.HeaderComponent;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage<P extends BasePage<P>> {

    private final SelenideElement PAGE_HEADER = $("header[aria-hidden='false']");

    public abstract P waitPageLoaded();

    public HeaderComponent header() {
        return new HeaderComponent(PAGE_HEADER);
    }
}

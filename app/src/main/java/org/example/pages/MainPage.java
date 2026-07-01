package org.example.pages;


public class MainPage extends BasePage<MainPage> {

    @Override
    public MainPage waitPageLoaded() {
        header().shouldVisible();
        return this;
    }
}

package org.example.utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Rectangle;

public class LayoutUtils {

    private LayoutUtils() {}

    public static boolean isHorizontallyAligned(SelenideElement upElement, SelenideElement downElement) {
        Rectangle rectUp = upElement.getRect();
        Rectangle rectDown = downElement.getRect();

        int leftUpElement = rectUp.getX();
        int rightUpElement = leftUpElement + rectUp.getWidth();

        int leftBottomElement = rectDown.getX();
        int rightBottomElement = leftBottomElement + rectDown.getWidth();

        int allowedDelta = 5;

        boolean leftEdge = Math.abs(leftUpElement - leftBottomElement) <= allowedDelta;
        boolean rightEdge = Math.abs(rightUpElement - rightBottomElement) <= allowedDelta;

        return leftEdge && rightEdge;
    }
}

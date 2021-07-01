package com.planittesting.automation.model.components.dialogs;

import com.planittesting.automation.model.pages.HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;

public class LogoutDialog {
    private WebElement rootElement;

    public LogoutDialog(WebElement rootElement) {
        this.rootElement = rootElement;
    }

    public HomePage clickLogoutButton() {
        rootElement.findElement(By.className("btn-success")).click();
        return new HomePage(((WrapsDriver)rootElement).getWrappedDriver());
    }
}

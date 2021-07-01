package com.planittesting.automation.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage<HomePage> {

    public HomePage(WebDriver driver) {
        super(driver);
    }
 
    public String getHeaderText() {
        return driver.findElement(By.cssSelector(".hero-unit h1")).getText();
    }
}

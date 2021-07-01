package com.planittesting.automation.model.pages;

import com.planittesting.automation.model.components.dialogs.LoginDialog;
import com.planittesting.automation.model.components.dialogs.LogoutDialog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage<T> {
    
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage clickHomePageMenu() {
        driver.findElement(By.cssSelector("nav-home a")).click();
        return new HomePage(driver);
    }

    public ContactPage clickContactMenu() {
        driver.findElement(By.cssSelector("#nav-contact a")).click();
        return new ContactPage(driver);
    }

    public ShopPage clickShopMenu() {
        driver.findElement(By.cssSelector("#nav-shop a")).click();
        return new ShopPage(driver);
    }

    @SuppressWarnings("unchecked")
    public LoginDialog<T> clickLoginMenu() {
        driver.findElement(By.cssSelector("#nav-login a")).click();
        return new LoginDialog<T>(driver.findElement(By.className("popup")), (T)this);
    }

    public LogoutDialog clickLogoutMenu() {
        driver.findElement(By.cssSelector("#nav-logout a")).click();
        return new LogoutDialog(driver.findElement(By.className("popup")));
    }

    public String getUser() {
        var elements = driver.findElements(By.cssSelector("#nav-user .user"));
        if(elements.isEmpty()) {
            return "";
        }
        return elements.get(0).getText();
    }

}

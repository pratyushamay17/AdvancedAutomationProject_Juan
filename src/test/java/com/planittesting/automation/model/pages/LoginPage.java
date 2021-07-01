package com.planittesting.automation.model.pages;

import com.planittesting.automation.model.data.LoginData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage<LoginPage>{

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public LoginPage setUsername(String name) {
        driver.findElement(By.id("loginUserName")).sendKeys(name);
        return this;
    }

    public LoginPage setPassword(String password) {
        driver.findElement(By.id("loginPassword")).sendKeys(password);
        return this;
    }

    public LoginPage setLoginData(LoginData loginData) {
        return setUsername(loginData.username()).setPassword(loginData.password());
    }
    
    public LoginPage clickLoginButton() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
        return this;
    }
}

package com.planittesting.automation.model.pages;

import com.planittesting.automation.model.data.ContactData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactPage extends BasePage<ContactPage> {

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public ContactPage clickSubmitButton() {
        driver.findElement(By.className("btn-primary")).click();
        return this;
    }

    public String getForenameError() {
        return getErrorText(By.id("forename-err"));
    }

    public String getEmailError() {
        return getErrorText(By.id("email-err"));
    }

    public String getMessageError() {
        return getErrorText(By.id("message-err"));
    }

    private String getErrorText(By locator) {
        var errors = driver.findElements(locator);
        if(errors.isEmpty()) {
            return "";
        }
        return errors.get(0).getText();
    }

    public ContactPage setForename(String forename) {
        driver.findElement(By.id("forename")).sendKeys(forename);
        return this;
    }

    public ContactPage setSurname(String Surname) {
        driver.findElement(By.id("surname")).sendKeys(Surname);
        return this;
    }

    public ContactPage setEmail(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
        return this;
    }

    public ContactPage setTelephone(String telephone) {
        driver.findElement(By.id("telephone")).sendKeys(telephone);
        return this;
    }

    public ContactPage setMessage(String message) {
        driver.findElement(By.id("message")).sendKeys(message);
        return this;
    }

    public ContactPage setContactData(ContactData contactData) {
        return setForename(contactData.forename())
              .setSurname(contactData.surname())
              .setEmail(contactData.email())
              .setTelephone(contactData.telephone())
              .setMessage(contactData.message());
    }
    
    public String getSuccessMessage() {
        return new WebDriverWait(driver, 60)
            .until(d -> d.findElement(By.className("alert-success")))
            .getText();
    }
}

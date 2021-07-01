package com.planittesting.automation.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.planittesting.automation.model.data.ContactData;
import com.planittesting.automation.model.pages.HomePage;
import com.planittesting.automation.tests.dataProviders.DBContactDataProvider;
import com.planittesting.automation.tests.dataProviders.WithTestFile;
import com.planittesting.automation.tests.dataProviders.YamlContactDataProvider;
import com.planittesting.automation.tests.dataProviders.CsvToContactData;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

class ContactTests extends BaseTests {
    
    @Test
    void validateMandatoryErrors() {
        var contactPage = open(HomePage.class)
            .clickContactMenu()
            .clickSubmitButton();
        assertEquals("Forename is required", contactPage.getForenameError());
        assertEquals("Email is required", contactPage.getEmailError());
        assertEquals("Message is required", contactPage.getMessageError());
    }

    @Test
    void validateErrorsFix() {
        var contactPage = open(HomePage.class)
            .clickContactMenu()
            .clickSubmitButton()
            .setForename("juan")
            .setEmail("hsdgs@hdgd.com")
            .setMessage("Hello");
        assertEquals("", contactPage.getForenameError());
        assertEquals("", contactPage.getEmailError());
        assertEquals("", contactPage.getMessageError());
    }

    @Test
    void validateSuccessfulSubmission() {
        var message = open(HomePage.class)
            .clickContactMenu()
            .setForename("juan")
            .setEmail("hsdgs@hdgd.com")
            .setMessage("Hello")
            .clickSubmitButton()
            .getSuccessMessage();
        assertEquals("Thanks juan, we appreciate your feedback.", message);
    }

    @ParameterizedTest
    @CsvSource({
        "juan,,a@b.com,,hello",
        "florez,,c@d.com,,world"
    })
    void validateSuccessfulSubmissionDataDrivenInline(@CsvToContactData ContactData contactData) {
        var message = open(HomePage.class)
            .clickContactMenu()
            .setContactData(contactData)
            .clickSubmitButton()
            .getSuccessMessage();
        assertEquals("Thanks "+contactData.forename()+", we appreciate your feedback.", message);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/contact_data.csv", numLinesToSkip = 1)
    void validateSuccessfulSubmissionDataDrivenFromFile(@CsvToContactData ContactData contactData) {
        var message = open(HomePage.class)
            .clickContactMenu()
            .setContactData(contactData)
            .clickSubmitButton()
            .getSuccessMessage();
        assertEquals("Thanks "+contactData.forename()+", we appreciate your feedback.", message);
    }

    @ParameterizedTest
    @ArgumentsSource(DBContactDataProvider.class)
    void validateSuccessfulSubmissionDataDrivenFromDB(ContactData contactData) {
        var message = open(HomePage.class)
            .clickContactMenu()
            .setContactData(contactData)
            .clickSubmitButton()
            .getSuccessMessage();
        assertEquals("Thanks "+contactData.forename()+", we appreciate your feedback.", message);
    }

    @ParameterizedTest
    @ArgumentsSource(YamlContactDataProvider.class)
    @WithTestFile("contact_data.yaml")
    void validateSuccessfulSubmissionDataDrivenFromYaml(ContactData contactData) {
        var message = open(HomePage.class)
            .clickContactMenu()
            .setContactData(contactData)
            .clickSubmitButton()
            .getSuccessMessage();
        assertEquals("Thanks "+contactData.forename()+", we appreciate your feedback.", message);
    }

}

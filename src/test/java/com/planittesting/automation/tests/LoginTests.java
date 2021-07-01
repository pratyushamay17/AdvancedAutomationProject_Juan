package com.planittesting.automation.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.planittesting.automation.model.data.LoginData;
import com.planittesting.automation.model.pages.HomePage;
import com.planittesting.automation.tests.dataProviders.CsvToLoginData;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LoginTests extends BaseTests {
    
    @Test
    void validateSucessfulLogin() {
        String user = open(HomePage.class)
            .clickLoginMenu()
            .login(new LoginData("juan", "letmein"))
            .getUser();
        assertEquals("prats", user);
    }

    @ParameterizedTest
    @CsvSource({"juan,letmein"})
    void validateSucessfulLogout(@CsvToLoginData LoginData loginData) {
        String user = open(HomePage.class)
            .clickLoginMenu()
            .login(loginData)
            .clickLogoutMenu()
            .clickLogoutButton()
            .getUser();
        assertEquals("", user);
    }
}

package com.planittesting.automation.tests;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

import com.planittesting.automation.driver.AbstractDriverFactory;
import com.planittesting.automation.environment.EnvironmentVariables;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;

@Execution(ExecutionMode.CONCURRENT)
public abstract class BaseTests {
    protected WebDriver driver;

    @RegisterExtension
    AfterEachTestProcessor afterEachTestProcessor = new AfterEachTestProcessor();

    @BeforeEach
    public void setup() throws ReflectiveOperationException {
        // Read configuration from env
        var browser = EnvironmentVariables.getBrowser();
        var wait = EnvironmentVariables.getImplicitWait();
        var url = EnvironmentVariables.getUrl();
        var headless = EnvironmentVariables.isHeadless();
        var gridUrl = EnvironmentVariables.getGridUrl();

        // Create driver
        driver = new AbstractDriverFactory().withGridUrl(gridUrl).withHeadless(headless).getInstance(browser);
        driver.manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to(url);
    }

    @AfterEach
    public void shutdown() {
        afterEachTestProcessor.setDriver(driver);
    }

    protected <T> T open(Class<T> clazz)  {
        try {
            return clazz.getConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}

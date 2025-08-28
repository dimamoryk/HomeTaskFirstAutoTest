package com.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.junit.jupiter.api.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public abstract class BaseTest {
    protected static final String BASE_URL = "https://otus.home.kartushin.su/form.html";
    protected WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME).setLevel(Level.INFO);

        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Универсальный метод ввода текста в поле
    protected void inputText(WebDriver driver, By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    // Универсальный метод клика по элементу
    protected void clickElement(WebDriver driver, By locator) {
        driver.findElement(locator).click();
    }
}

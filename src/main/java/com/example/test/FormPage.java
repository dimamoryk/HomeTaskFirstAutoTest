package com.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static com.example.test.BaseTest.BASE_URL;

public class FormPage {

    private static final Logger logger = LoggerFactory.getLogger(FormPage.class);
    private WebDriver driver;

    public FormPage(WebDriver driver) {
        this.driver = driver;
        driver.get(BASE_URL);
    }


    public void fillForm(String name, String email, String password, String birthDate, String languageSkill) {
        // Имя пользователя
        WebElement usernameInput = driver.findElement(By.id("username"));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(usernameInput));
        usernameInput.sendKeys(name);

        // Email
        WebElement emailInput = driver.findElement(By.id("email"));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.sendKeys(email);

        // Пароль
        WebElement passwordInput = driver.findElement(By.id("password"));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(password);

        // Дата рождения
        WebElement birthDateInput = driver.findElement(By.id("birth-date"));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(birthDateInput));
        birthDateInput.sendKeys(birthDate);

        // Уровень владения языком
        selectLanguageSkill(languageSkill);

        // Подтверждение пароля
        checkPasswordMatch(password);
    }


    private void selectLanguageSkill(String skill) {
        WebElement dropdownOption = driver.findElement(By.xpath("//select[@id='language-skill']/option[.='" + skill + "']"));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(dropdownOption));
        dropdownOption.click();
    }


    private void checkPasswordMatch(String expectedPassword) {
        WebElement confirmField = driver.findElement(By.id("confirm-password"));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(confirmField));
        confirmField.sendKeys(expectedPassword);
        if (!expectedPassword.equals(confirmField.getAttribute("value"))) {
            logger.error("Пароли не совпадают!");
            throw new IllegalStateException("Пароли не совпадают!");
        }
    }


    public void submitForm() {
        WebElement submitButton = driver.findElement(By.id("submit-button"));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result-message")));
    }


    public String getResultMessage() {
        WebElement resultMessage = driver.findElement(By.id("result-message"));
        return resultMessage.getText();
    }
}

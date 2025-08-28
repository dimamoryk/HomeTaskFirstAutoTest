package com.example.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(SeleniumTest.class);

    @Test
    public void testFormSubmission() {
        String username = System.getenv("USERNAME");
        String email = System.getenv("EMAIL");
        String password = System.getenv("PASSWORD");

        if(username == null || email == null || password == null) {
            logger.warn("One or more environment variables are missing.");
            return;
        }

        try {
            FormPage formPage = new FormPage(driver);

            // Заполняем форму
            formPage.fillForm(
                    username,
                    email,
                    password,
                    "1990-01-01",
                    "Advanced"
            );

            // Нажимаем кнопку отправки
            formPage.submitForm();

            // Проверяем успешность отправки
            assertEquals("Форма успешно отправлена.", formPage.getResultMessage());
        } catch(Exception e) {
            logger.error("Error during test execution:", e); // Полностью записываем стектрейс
            throw e; // Можно оставить, если нужен повторный выброс исключения вверх по цепочке
        }
    }
}

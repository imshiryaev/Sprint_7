package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.model.Courier;
import org.example.steps.CourierSteps;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourierLoginTest {

    private CourierSteps courierSteps = new CourierSteps();
    private Courier courier;

    @Before
    public void setUp() {

        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
    }

    @Test
    @DisplayName("Проверка наличия id курьера в теле ответа при успешном запросе")
    public void shouldReturnId() {
        courierSteps
                .createCourier(courier);

        courierSteps
                .loginCourier(courier)
                .statusCode(200)
                .body("id", Matchers.notNullValue());
    }


    @Test
    @DisplayName("Проверка авторизации под несуществущим пользователем")
    public void shouldReturnErrorNonExistentUser() {
        courierSteps.loginCourier(courier).statusCode(404).body("message", Matchers.is("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Проверка авторизации без логина")
    public void shouldReturnErrorWithoutLogin() {
        courierSteps
                .createCourier(courier);

        courier.setLogin("");

        courierSteps.
                loginCourier(courier).statusCode(400).body("message", Matchers.is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Проверка авторизации без пароля")
    public void shouldReturnErrorWithoutPassword() {
        courierSteps
                .createCourier(courier);

        courier.setPassword("");

        courierSteps.
                loginCourier(courier).statusCode(400).body("message", Matchers.is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Проверка авторизации c неправильным логином")
    public void shouldReturnErrorIncorrectLogin() {
        courierSteps
                .createCourier(courier);

        courier.setLogin("htnbvzaqefth");

        courierSteps.
                loginCourier(courier).statusCode(404).body("message", Matchers.is("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Проверка авторизации c неправильным паролем")
    public void shouldReturnErrorIncorrectPassword() {
        courierSteps
                .createCourier(courier);

        courier.setPassword("htnbvzaqefth");

        courierSteps.
                loginCourier(courier).statusCode(404).body("message", Matchers.is("Учетная запись не найдена"));
    }


    @After
    public void tearDown() {
        Integer id = courierSteps.loginCourier(courier).extract().body().path("id");
        if (id != null) {
            courier.setId(id);
            courierSteps.deleteCourier(courier);
        }
    }
}

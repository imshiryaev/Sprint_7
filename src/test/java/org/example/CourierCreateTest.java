package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.model.Courier;
import org.example.steps.CourierSteps;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CourierCreateTest {

    private CourierSteps courierSteps = new CourierSteps();
    private Courier courier;

    @Before
    public void setUp() {
        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
    }

    @Test
    @DisplayName("Проверка создания курьера")
    public void shouldReturnStatusCodeCreated() {
        courierSteps.createCourier(courier)
                .statusCode(201);
    }

    @Test
    @DisplayName("Проверка тела ответа при создании курьера")
    public void shouldReturnOkTrue() {
        courierSteps.createCourier(courier)
                .body("ok", Matchers.is(true));
    }

    @Test
    @DisplayName("Проверка создания одинаковых курьеров")
    public void shouldReturnErrorWhenCreatingIdenticalCouriers() {
        courierSteps.createCourier(courier);
        courierSteps.createCourier(courier).statusCode(409).body("message", Matchers.is("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Проверка создания курьера без логина")
    public void shouldReturnErrorWithoutLogin() {
        courier.setLogin("");
        courierSteps.createCourier(courier).statusCode(400).body("message", Matchers.is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Проверка создания курьера без пароля")
    public void shouldReturnErrorWithoutPassword() {
        courier.setPassword("");
        courierSteps.createCourier(courier).statusCode(400).body("message", Matchers.is("Недостаточно данных для создания учетной записи"));
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
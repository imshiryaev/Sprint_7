package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.example.model.Courier;

import static io.restassured.RestAssured.given;
import static org.example.config.RestConfig.URL;

public class CourierSteps {

    public static final String COURIER = "/api/v1/courier/";
    public static final String LOGIN= "/api/v1/courier/login";
    public static final String COURIER_ID = "/api/v1/courier/{id}";

    @Step("Создание курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(URL)
                .body(courier)
                .when()
                .post(COURIER)
                .then();
    }

    @Step("Авторизация курьера")
    public ValidatableResponse loginCourier(Courier courier) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(URL)
                .body(courier)
                .when()
                .post(LOGIN)
                .then();
    }
    @Step("Удаление курьера")
    public void deleteCourier(Courier courier) {
        given()
                .contentType(ContentType.JSON)
                .baseUri(URL)
                .pathParams("id", courier.getId())
                .when()
                .delete(COURIER_ID)
                .then();
    }
}

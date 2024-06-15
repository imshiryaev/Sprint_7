package org.example.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.example.model.Order;

import static io.restassured.RestAssured.given;
import static org.example.config.RestConfig.URL;

public class OrderSteps {
    public static final String ORDERS = "/api/v1/orders";


    @Step("Создание заказа")
    public ValidatableResponse createOrder(Order order) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(URL)
                .body(order)
                .when()
                .post(ORDERS)
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrdersList(){
        return given()
                .baseUri(URL)
                .when()
                .get(ORDERS)
                .then();
    }
}

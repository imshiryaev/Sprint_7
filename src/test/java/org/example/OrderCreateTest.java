package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.model.Order;
import org.example.steps.OrderSteps;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    public OrderCreateTest(String firstName, String lastName, String address, Integer metroStation, String phone, Integer rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    private String firstName;
    private String lastName;
    private String address;
    private Integer metroStation;
    private String phone;
    private Integer rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;


    OrderSteps orderSteps = new OrderSteps();

    Order order;


    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"ТестовоеИмя", "ТестоваяФамилия", "ТестовыйАдрес", 4, "+79253454645", 2, "2024-06-14", "ТестовыйКомментарий", List.of("BLACK")},
                {"ТестовоеИмя", "ТестоваяФамилия", "ТестовыйАдрес", 4, "+79253454645", 5, "2024-06-25", "ТестовыйКомментарий", List.of("GRAY")},
                {"ТестовоеИмя", "ТестоваяФамилия", "ТестовыйАдрес", 4, "+79253454645", 5, "2024-06-25", "ТестовыйКомментарий", List.of("BLACK","GRAY")},
                {"ТестовоеИмя", "ТестоваяФамилия", "ТестовыйАдрес", 4, "+79253454645", 5, "2024-06-25", "ТестовыйКомментарий", null},
        };
    }

    @Before
    public void setUp(){
        order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }

    @Test
    @DisplayName("Проверка создания заказа")
    public void shouldReturnStatusCodeCreated(){
        orderSteps.createOrder(order).statusCode(201).body("track", Matchers.notNullValue());
    }

}



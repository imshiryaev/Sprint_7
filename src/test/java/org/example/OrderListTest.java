package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.steps.OrderSteps;
import org.hamcrest.Matchers;
import org.junit.Test;

public class OrderListTest {

    OrderSteps orderSteps = new OrderSteps();

    @Test
    @DisplayName("Проверка получения списка заказов")
    public void shouldReturnOrdersList(){
        orderSteps.getOrdersList().statusCode(200).body("orders", Matchers.notNullValue());
    }

}

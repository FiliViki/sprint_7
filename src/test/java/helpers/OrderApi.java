package helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderApi {
    private static final String BASE_URL = "/api/v1/orders";

    @Step("Создание заказа")
    public Response createOrder(OrderTestData data) {
        return given()
                .header("Content-Type", "application/json")
                .body(data)
                .post(BASE_URL);
    }
    @Step("Получение списка заказов")
    public Response getOrders(){
        return given()
                .header("Content-Type", "application/json")
                .get(BASE_URL);
    }
}

import helpers.OrderApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrderListTest {

    private final OrderApi api = new OrderApi();

    public GetOrderListTest() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Получение списка заказов")
    public void getOrderListTest() {
        Response response = api.getOrders();
        response.then().statusCode(SC_OK).body("orders", notNullValue());
    }

}
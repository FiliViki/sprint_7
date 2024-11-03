import helpers.OrderApi;
import helpers.OrderTestData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final OrderApi api = new OrderApi();
    private final OrderTestData orderData;

    public CreateOrderTest(List<String> color) {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        this.orderData = new OrderTestData(color);
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}")
    public static Object[] getOrderData() {
        return new Object[]{
                List.of("BLACK"),
                List.of("GREY"),
                List.of("BLACK", "GREY"),
                List.of() // без указания цвета
        };
    }

    @Test()
    @DisplayName("Создание заказа с параметризацией")
    public void createOrderTest() {
        Response response = api.createOrder(orderData);
        response.then().statusCode(SC_CREATED).body("track", notNullValue());
    }
}

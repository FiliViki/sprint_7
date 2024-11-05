import helpers.OrderApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;

public class GetOrderListTest extends BaseTest {

    private final OrderApi api = new OrderApi();


    @Test
    @DisplayName("Получение списка заказов")
    public void getOrderListTest() {
        Response response = api.getOrders();
        response.then().statusCode(SC_OK)
                .body("orders", notNullValue())
                .body("orders", not(empty()))
                .body("orders.size()", greaterThan(0));
    }

}
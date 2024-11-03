package helpers;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CourierApi {
    private static final String BASE_URL = "/api/v1/courier";

    @Step("Создание курьера: логин '{login}', пароль '{password}', имя '{firstName}'")
    public Response createCourier(String login, String password, String firstName) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("login", login);
        requestBody.put("password", password);
        requestBody.put("firstName", firstName);

        return given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(BASE_URL);

    }

    @Step("Логин курьера: логин '{login}', пароль '{password}'")
    public Response loginCourier(String login, String password) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("login", login);
        requestBody.put("password", password);

        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(BASE_URL + "/login");
    }

    @Step("Удаление курьера: логин '{login}'")
    public Response deleteCourier(String login, String password) {
        Response response = loginCourier(login, password);

        String id = response.jsonPath().getString("id");
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("id", id);

        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .delete(BASE_URL + "/" + id);
    }
}
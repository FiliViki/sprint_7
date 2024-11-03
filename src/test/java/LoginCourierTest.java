import helpers.CourierApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


@DisplayName("Логин курьера")
public class LoginCourierTest {
    private final String login = "Andrey123";
    private final String password = "qwerty123";

    CourierApi api = new CourierApi();

    public LoginCourierTest() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Before()
    public void setUp() {
        api.createCourier(login, password, "Andrey");
    }

    @Test()
    @DisplayName("Курьер должен успешно авторизоваться")
    public void loginCourier(){
        Response response = api.loginCourier(login, password);

        response.then().statusCode(SC_OK).body("id", notNullValue());
    }

    @Test()
    @DisplayName("Курьер должен иметь пароль")
    public void loginCourierWithoutPassword() {
        Response response = api.loginCourier(login, "");

        response.then().statusCode(SC_BAD_REQUEST).body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test()
    @DisplayName("Курьер должен иметь логин")
    public void loginCourierWithoutLogin() {
        Response response = api.loginCourier("", password);

        response.then().statusCode(SC_BAD_REQUEST).body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test()
    @DisplayName("Курьер не должен быть авторизован с несуществующим логином")
    public void loginCourierWithWrongLogin() {
        Response response = api.loginCourier(login + "wrong", password);

        response.then().statusCode(SC_NOT_FOUND).body("message", equalTo("Учетная запись не найдена"));
    }

    @Test()
    @DisplayName("Курьер не должен быть авторизован с неправильным паролем")
    public void loginCourierWithWrongPassword() {
        Response response = api.loginCourier(login, password + "wrong");

        response.then().statusCode(SC_NOT_FOUND).body("message", equalTo("Учетная запись не найдена"));
    }

    @After()
    public void removeCourier() {
        api.deleteCourier(login, password);
    }

}

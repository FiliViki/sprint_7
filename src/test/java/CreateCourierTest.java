import helpers.CourierApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;

@DisplayName("Создание нового курьера")
public class CreateCourierTest extends BaseTest {
    private final String login = "Andrey123";
    private final String password = "qwerty123";

    CourierApi api = new CourierApi();

    @Test()
    @DisplayName("Курьер должен быть создан")
    public void createCourier() {
        Response response = api.createCourier(login, password, "Andrey");

        response.then().statusCode(SC_CREATED).body("ok", equalTo(true));
    }

    @Test()
    @DisplayName("Нельзя создать два одинаковых курьера")
    public void createCourierDouble() {
        Response responseOriginal = api.createCourier(login, password, "Andrey");

        responseOriginal.then().statusCode(SC_CREATED);

        Response responseDouble = api.createCourier(login, password, "Andrey");

        responseDouble.then().statusCode(SC_CONFLICT).body("message", equalTo("Этот логин уже используется"));
    }


    @Test()
    @DisplayName("Курьер должен иметь логин")
    public void createCourierWithoutLogin() {
        Response response = api.createCourier("", password, "Andrey");

        response.then().statusCode(SC_BAD_REQUEST).body("message", equalTo("Недостаточно данных для входа"));
    }


    @Test()
    @DisplayName("Курьер должен иметь пароль")
    public void createCourierWithoutPassword() {
        Response response = api.createCourier(login, "", "Andrey");

        response.then().statusCode(SC_BAD_REQUEST).body("message", equalTo("Недостаточно данных для входа"));
    }


    @Test()
    @DisplayName("Курьер должен иметь имя")
    public void createCourierWithoutName() {
        Response response = api.createCourier(login, password, "");

        response.then().statusCode(SC_BAD_REQUEST).body("message", equalTo("Недостаточно данных для входа"));
    }

    @After()
    public void removeCourier() {
        api.deleteCourier(login, password);
    }
}
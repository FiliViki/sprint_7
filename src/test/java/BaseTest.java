import io.restassured.RestAssured;

public abstract class BaseTest {
    public BaseTest() {

        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }
}

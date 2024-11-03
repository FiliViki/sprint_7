package helpers;

import java.util.List;

public class OrderTestData {
    public final String firstName = "Andrey";
    public final String lastName = "Ivanov";
    public final String address = "Preo 8";
    public final String metroStation = "Preobragenskaya";
    public final String phone = "+79990001122";
    public final int rentTime = 3;
    public final String deliveryDate = "2024-11-03";
    public final String comment = "i love cucumber";
    public final List<String> color;

    public OrderTestData(List<String> color){
        this.color = color;
    }
}

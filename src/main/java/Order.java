import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;


    public Order(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
//        this.deliveryDate = deliveryDate;
        this.comment = comment;
    }

    public static Order getDefaultOrder() {
        return new Order("Тестик",
                "Тестовый",
                "Bolshaya street",
                "4",
                "777777777",
                2,
                "очень жду самокат!");
    }

    public Order setDeliveryDateCustom() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusDays(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String deliveryDate = localDate.format(formatter);
        this.deliveryDate = deliveryDate;
        return this;
    }

    public Order setColor(List<String> color) {
        this.color = color;
        return this;
    }
}
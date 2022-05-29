import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreateTest extends BaseTest{

    @After
    public void tearDown() {
        OrderApi.cancelOrder(track);
    }

    private int track;

    private List<String> color;

    public OrderCreateTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {List.of("")},
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK", "GREY")},
        };
    }

    @Test
    @DisplayName("Check status code and body when creating order with different colors")
    public void createOrderWithDifferentColors() {
        Order order = Order.getDefaultOrder().setColor(color).setDeliveryDateCustom();
        track = OrderApi.createOrder(order)
                .then().log().all()
                .assertThat().statusCode(201)
                .body("track", notNullValue())
                .extract().path("track");
    }

}
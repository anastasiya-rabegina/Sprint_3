import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class OrderGetTest extends BaseTest{

    private int track;
    private OrderApi orderApi;


    @After
    public void tearDown() {
        OrderApi.cancelOrder(track);
    }

    @Test
    @DisplayName("Check status code and body when getting all orders without params")
    public void checkMessageWhen() {
        Order order = Order.getDefaultOrder().setDeliveryDateCustom();
        track = OrderApi.createOrder(order)
                .then()
                .extract().path("track");
        OrderApi.getOrder()
                .then().log().ifStatusCodeMatches(greaterThan(200))
                .assertThat().statusCode(200)
                .body("orders", notNullValue());
    }
}
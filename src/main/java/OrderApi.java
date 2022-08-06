import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderApi {

    private static final String ROOT = "/orders";
    private static final String CANCEL = ROOT + "/cancel";

    public static Response createOrder(Order order) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(order)
                .when()
                .post(ROOT);
    }

    public static Response cancelOrder(int track) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(track)
                .when()
                .put(CANCEL);
    }

    public static Response getOrder() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get(ROOT);
    }
}

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierApi {

    private static final String ROOT = "/courier";
    private static final String LOGIN = ROOT + "/login";
    private static final String COURIER = ROOT + "/courierId";

    public static Response createCourier(Courier courier) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(ROOT);
    }

    public static int loginAndGetCourierId(CourierCredentials creds) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().extract().body().path("id");
    }

    public static Response login(CourierCredentials creds) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(creds)
                .when()
                .post(LOGIN);
    }

    public static Response deleteCourier(int courierId) {
        return given()
                .delete(COURIER);
    }
}
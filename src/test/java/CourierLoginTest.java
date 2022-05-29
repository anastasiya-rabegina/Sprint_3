import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotEquals;

public class CourierLoginTest extends BaseTest{

    private int courierId;

    @After
    public void teardown() {
        CourierApi.deleteCourier(courierId);
    }

    @Test
    @DisplayName("Check id is returned for successful login")
    public void courierCanLogin() {
        Courier courier = Courier.getRandom();
        CourierApi.createCourier(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = CourierApi.loginAndGetCourierId(creds);

        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Check status code and message when courier logs in without Login")
    public void courierCannotAuthWithoutLogin() {
        CourierCredentials creds = new CourierCredentials(null, "1233");
        CourierApi.login(creds)
                .then().assertThat().statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check status code and message when courier logs in without password")
    public void courierCannotAuthWithoutPass() {
        CourierCredentials creds = new CourierCredentials("testik", null);
        CourierApi.login(creds)
                .then().assertThat().statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check status code and message when courier logs in with incorrect data")
    public void courierCannotAuthWithIncorrectData() {
        CourierCredentials creds = new CourierCredentials("testik", "hh");
        CourierApi.login(creds)
                .then().assertThat().statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
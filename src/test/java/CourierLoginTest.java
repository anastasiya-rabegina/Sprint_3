import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotEquals;

public class CourierLoginTest extends BaseTest{

    private int courierId;

    private Courier courier;
    private CourierCredentials creds;

    @Before
    public void init() {
        courier = Courier.getRandom();
        creds = CourierCredentials.from(courier);
    }

    @After
    public void teardown() {
        CourierApi.deleteCourier(courierId);
    }

    @Test
    @DisplayName("Check id is returned for successful login")
    public void courierCanLogin() {
        CourierApi.createCourier(courier);
        courierId = CourierApi.loginAndGetCourierId(creds);

        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Check status code and message when courier logs in without Login")
    public void courierCannotAuthWithoutLogin() {
        creds.setLogin(null);
        CourierApi.login(creds)
                .then().assertThat().statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check status code and message when courier logs in without password")
    public void courierCannotAuthWithoutPass() {
        creds.setPassword(null);
        CourierApi.login(creds)
                .then().assertThat().statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check status code and message when courier logs in with incorrect data")
    public void courierCannotAuthWithIncorrectData() {
        CourierApi.login(creds)
                .then().assertThat().statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
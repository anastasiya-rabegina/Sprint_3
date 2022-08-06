import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.*;

public class CourierCreateTest extends BaseTest{

    private CourierApi courierApi;
    private int courierId;

    @After
    public void teardown() {
        CourierApi.deleteCourier(courierId);
    }

    @Test
    @DisplayName("Check response code and message when creating courier")
    public void createCourierCheckRespCode() {
        Courier courier = Courier.getRandom();
        CourierApi.createCourier(courier)
                .then().assertThat().statusCode(201)
                .body("ok", equalTo(true));

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierApi.loginAndGetCourierId(creds);

        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Check status code when creating duplicate courier")
    public void cannotCreateTwoIdendicalCouriers() {
        Courier courier = Courier.getRandom();
        CourierApi.createCourier(courier);
        CourierApi.createCourier(courier)
                .then().assertThat().statusCode(409);

    }

    @Test
    @DisplayName("Check status code and message when creating courier without login")
    public void courierCannotBeCreatedWithoutLogin() {
        Courier courier = Courier.getRandom();
        courier.setLogin(null);
        CourierApi.createCourier(courier)
                .then().assertThat().statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Check status code and message when creating courier without password")
    public void courierCannotBeCreatedWithoutPass() {
        Courier courier = Courier.getRandom();
        courier.setPassword(null);
        CourierApi.createCourier(courier)
                .then().assertThat().statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Check status code and message when creating courier without firstName")
    public void courierCannotBeCreatedWithoutFirstName() {
        Courier courier = Courier.getRandom();
        courier.setFirstName(null);
        CourierApi.createCourier(courier)
                .then().assertThat().statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
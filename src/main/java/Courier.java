import com.github.javafaker.Faker;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Builder
@Data
public class Courier {
    private String login;
    private String password;
    private String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    static Faker faker = new Faker();

    public static Courier getRandom() {
        String login = RandomStringUtils.randomAlphanumeric(10);
        String password = faker.internet().password(9, 10);
        String firstName = faker.name().firstName();

        return new Courier(login, password, firstName);
    }
}
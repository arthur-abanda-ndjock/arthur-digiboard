package media.soft;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;

@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class AutoConfigIntegrationTest {

    /**
     * Encapsulates the random port the test server is listening on.
     */
    @LocalServerPort
    private int port;

    @Test
    public void givenNoAuthentication_whenAccessHome_thenUnauthorized() {
        int statusCode = RestAssured.get("http://localhost:" + port).statusCode();
        assertEquals(HttpStatus.OK.value(), statusCode);
    }

    // @Test
    // public void givenAuthentication_whenAccessHome_thenOK() {
    // int statusCode = RestAssured.given().auth().basic("john",
    // "123").get("http://localhost:" + port).statusCode();
    // assertEquals(HttpStatus.OK.value(), statusCode);
    // }
}
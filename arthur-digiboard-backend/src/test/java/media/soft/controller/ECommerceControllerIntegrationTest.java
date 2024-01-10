package media.soft.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import io.restassured.RestAssured;
import media.soft.Application;

@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
class ECommerceControllerIntegrationTest {

        @LocalServerPort
        private int port;
        private final String localhost = "http://localhost:";

        @Test
        void givenNothing_WhenAccessHome_thenSuccess() {
                int statusCode = RestAssured.get(localhost + port).statusCode();
                assertEquals(HttpStatus.OK.value(), statusCode);
        }

        @Test
        void givenNothing_WhenAccessEcommerceOrders_thenSuccess() {
                int statusCode = RestAssured
                                .get(localhost + port + ECommerceController.API_PREFIX + ECommerceController.ORDERS)
                                .statusCode();
                assertEquals(HttpStatus.OK.value(), statusCode);
                statusCode = RestAssured
                                .get(localhost + port + ECommerceController.API_PREFIX
                                                + ECommerceController.ORDERS_BY_DATE)
                                .statusCode();
                assertEquals(HttpStatus.OK.value(), statusCode);

                statusCode = RestAssured
                                .get(localhost + port + ECommerceController.API_PREFIX
                                                + ECommerceController.ORDERS_SMR_RCT)
                                .statusCode();
                assertEquals(HttpStatus.OK.value(), statusCode);

                statusCode = RestAssured
                                .get(localhost + port + ECommerceController.API_PREFIX
                                                + ECommerceController.ORDERS_WK_SALES)
                                .statusCode();
                assertEquals(HttpStatus.OK.value(), statusCode);

        }

        @Test
        void givenNothing_WhenAccessEcommerceShipping_thenSuccess() {
                int statusCode = RestAssured
                                .get(localhost + port + ECommerceController.API_PREFIX + ECommerceController.SHIPPING)
                                .statusCode();
                assertEquals(HttpStatus.OK.value(), statusCode);
        }

        @Test
        void givenOrderId_WhenAccessOrderApi_thenSuccess() {
                String orderId = "/1";
                int statusCode = RestAssured
                                .get(localhost + port + ECommerceController.API_PREFIX + ECommerceController.ORDERS
                                                + orderId)
                                .statusCode();
                assertEquals(HttpStatus.OK.value(), statusCode);
        }

        @Test
        void givenOrderId_WhenAccessOrderApi_thenFailure() {
                String orderId = "/1000000000";
                int statusCode = RestAssured
                                .get(localhost + port + ECommerceController.API_PREFIX + ECommerceController.ORDERS
                                                + orderId)
                                .statusCode();
                assertEquals(HttpStatus.NOT_FOUND.value(), statusCode);
        }

        @Test
        void givenOrderId_WhenAccessOrderApi_thenFailure2() {
                String orderId = "/1000000XXXX00";
                int statusCode = RestAssured
                                .get(localhost + port + ECommerceController.API_PREFIX + ECommerceController.ORDERS
                                                + orderId)
                                .statusCode();
                assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), statusCode);
        }
}
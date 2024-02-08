package media.soft.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import io.restassured.RestAssured;
import media.soft.model.Order;
import media.soft.model.PrdCustomer;
import media.soft.repository.OrdersRepositoryDao;
import media.soft.repository.PrdCustomersRepositoryDao;

@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Disabled("a Docker environment is necessary to run this testcase, hence test must be skipped when building locally")
public class ECommerceControllerContainerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ECommerceControllerContainerTest.class);

    @LocalServerPort
    private int port;
    private final String localhost = "http://localhost:";

    @Autowired
    OrdersRepositoryDao ordersRepositoryDao;

    @Autowired
    PrdCustomersRepositoryDao customersRepositoryDao;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

    @BeforeAll
    public static void setUp() {
        postgreSQLContainer.withReuse(true);
        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", postgreSQLContainer::getDriverClassName);
        registry.add("spring.liquibase.change-log", () -> "classpath:/db-test/changelogs/master-cl.yaml");

    }

    @AfterEach
    public void tearDown() {
        ordersRepositoryDao.deleteAll(); // clean the order table
        LOGGER.info("clean the order table after test");
    }

    @Test
    void givenTableIsEmpty_WhenGetAll_ThenCountIsZero() {
        List<Order> orders = ordersRepositoryDao.getAll();
        assertEquals(0, orders.size());
    }

    @Test
    void givenTableIsFed_WhenGetAll_ThenCountIsNonZero() {
        ordersRepositoryDao.deleteAll();
        customersRepositoryDao.deleteAll();
        // seeding the database
        this.getSampleCustomers().stream().forEach(customersRepositoryDao::insert);
        this.getSampleOrders().stream().forEach(ordersRepositoryDao::insert);

        // act
        int statusCode = RestAssured
                .get(localhost + port + ECommerceController.API_PREFIX + ECommerceController.ORDERS)
                .statusCode();
        // assert
        assertEquals(HttpStatus.OK.value(), statusCode);

        // act
        RestAssured
                .get(localhost + port + ECommerceController.API_PREFIX + ECommerceController.ORDERS)
                .then().body("$.", hasSize(this.getSampleOrders().size()));
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.liquibase.change-log=classpath:/db/changelogs/master-cl.yaml")
                    .applyTo(applicationContext);
        }
    }

    private List<PrdCustomer> getSampleCustomers() {

        List<PrdCustomer> customers = new ArrayList<>();
        customers.add(getSampleCustomer(1, "firstName1", "lastName1", 'M', "address1"));
        customers.add(getSampleCustomer(2, "firstName2", "lastName2", 'M', "address2"));
        customers.add(getSampleCustomer(3, "firstName3", "lastName3", 'F', "address3"));
        customers.add(getSampleCustomer(4, "firstName4", "lastName4", 'F', "address4"));
        customers.add(getSampleCustomer(5, "firstName5", "lastName5", 'F', "address5"));
        customers.add(getSampleCustomer(6, "firstName6", "lastName6", 'M', "address6"));
        customers.add(getSampleCustomer(7, "firstName7", "lastName7", 'F', "address7"));
        customers.add(getSampleCustomer(8, "firstName8", "lastName8", 'F', "address8"));
        customers.add(getSampleCustomer(9, "firstName9", "lastName9", 'M', "address9"));
        customers.add(getSampleCustomer(10, "firstName10", "lastName10", 'M', "address10"));
        return customers;
    }

    private PrdCustomer getSampleCustomer(int customerID, String firstName, String lastName, char sex, String address) {

        PrdCustomer prdCustomer = new PrdCustomer();
        prdCustomer.setCustomerID(customerID);
        prdCustomer.setFirstName(firstName);
        prdCustomer.setLastName(lastName);
        prdCustomer.setSex(sex);
        prdCustomer.setAddress(address);
        return prdCustomer;
    }

    private List<Order> getSampleOrders() {

        // Create sample orders with various product types on three unique dates
        Order ebookOrder1 = new Order(1, 1, "EBook", 1, LocalDate.now(), new BigDecimal("1.00"));
        Order ebookOrder2 = new Order(2, 2, "EBook", 2, LocalDate.now(), new BigDecimal("2.00"));

        Order softwareOrder1 = new Order(3, 3, "Software", 3, LocalDate.now(), new BigDecimal("3.00"));
        Order softwareOrder2 = new Order(4, 4, "Software", 4, LocalDate.now(), new BigDecimal("4.00"));

        Order musicOrder1 = new Order(5, 5, "DigitalMusic", 5, LocalDate.now(), new BigDecimal("5.00"));
        Order musicOrder2 = new Order(6, 6, "DigitalMusic", 6, LocalDate.now(), new BigDecimal("6.00"));

        Order courseOrder1 = new Order(7, 7, "OnlineCourse", 7, LocalDate.now().plusDays(1), new BigDecimal("7.00"));
        Order courseOrder2 = new Order(8, 8, "OnlineCourse", 8, LocalDate.now().plusDays(1), new BigDecimal("8.00"));

        Order streamingOrder1 = new Order(9, 9, "StreamingService", 9, LocalDate.now().plusDays(2),
                new BigDecimal("9.00"));
        Order streamingOrder2 = new Order(10, 10, "StreamingService", 10, LocalDate.now().plusDays(2),
                new BigDecimal("10.00"));

        List<Order> orders = Arrays.asList(
                ebookOrder1, ebookOrder2,
                softwareOrder1, softwareOrder2,
                musicOrder1, musicOrder2,
                courseOrder1, courseOrder2,
                streamingOrder1, streamingOrder2);

        return orders;
    }
}
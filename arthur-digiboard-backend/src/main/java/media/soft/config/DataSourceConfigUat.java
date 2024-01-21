package media.soft.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;

@Configuration
@Profile("uat")
public class DataSourceConfigUat {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfigUat.class);

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Bean
    public DefaultCredentialsProvider awsCredentialsProvider() {
        return DefaultCredentialsProvider.create();
    }

    @Bean
    public DataSource dataSource() {

        LOGGER.info("driverClassName: ".concat(driverClassName));
        LOGGER.info("dbUrl: ".concat(dbUrl));

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(dbUrl);
        return dataSource;
    }
}

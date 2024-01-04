package media.soft.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        String devEndpoint = "http://localhost:8080";
        devServer.setUrl(devEndpoint);
        devServer.setDescription("Host url in Development environment");

        Contact contact = new Contact();
        contact.setEmail("arthur-abanda@gmail.com");
        contact.setName("arthur abanda");
        contact.setUrl("https://www.arthur-abanda.com");

        License mitLicense = new License().name("Apache License 2.0")
                .url("https://choosealicense.com/licenses/apache-2.0/");

        Info info = new Info()
                .title("Digiboard")
                .version("1.0")
                .contact(contact)
                .description("API exposes endpoints for Digiboard backend")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}

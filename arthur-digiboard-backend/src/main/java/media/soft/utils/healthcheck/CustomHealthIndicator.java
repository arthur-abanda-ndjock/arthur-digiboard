package media.soft.utils.healthcheck;

import java.util.Random;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		// Your custom health check logic goes here
		boolean isHealthy = checkHealth(); // Implement your custom health check

		if (isHealthy) {
			return Health.up().withDetail("CustomComponent", "Custom component is healthy").build();
		} else {
			return Health.down().withDetail("CustomComponent", "Custom component is not healthy").build();
		}
	}

	private boolean checkHealth() {
		return new Random().nextInt(2) == 1 ? true : false; // Change this based on your actual health check logic
	}
}
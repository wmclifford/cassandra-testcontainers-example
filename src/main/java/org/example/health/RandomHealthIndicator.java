package org.example.health;

import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Sample custom {@link HealthIndicator} which includes additional details and
 * can be enabled and disabled via the application properties.
 */
@Component
@ConditionalOnEnabledHealthIndicator("random")
public class RandomHealthIndicator implements HealthIndicator {

  @Override
  public Health health() {
    final double chance = ThreadLocalRandom.current().nextDouble();
    final Health.Builder healthBuilder = Health.up();
    if (chance > 0.8) {
      healthBuilder.down();
    }
    return healthBuilder
      .withDetail("chance", chance)
      .withDetail("strategy", "thread-local")
      .build();
  }

}

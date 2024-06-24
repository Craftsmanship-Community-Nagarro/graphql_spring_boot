package com.craftmanship.graphql.country.configuration;

import static java.util.Objects.isNull;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

  @Value("${spring.application.name}")
  public String applicationName;

  @Override
  public Optional<String> getCurrentAuditor() {
    return isNull(this.applicationName) ? Optional.of("defaultUser") : Optional.of(this.applicationName);
  }
}

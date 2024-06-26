package com.craftmanship.graphql.country.feature.graphql;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Controller
@Slf4j
public class CountrySubscriptionController {

  private final Sinks.Many<Country> sink;

  public CountrySubscriptionController() {
    this.sink = Sinks.many().multicast().onBackpressureBuffer();
  }

  @Scheduled(fixedRate = 5000) // every five seconds
  public void generateCountry() {
    final Country country = new Country.CountryBuilder()
        .englishName(RandomStringUtils.randomAscii(6).replaceAll("\\s+", "A"))
        .id(1L)
        .build();

    log.info("Generated country: {}", country);
    this.sink.tryEmitNext(country);
  }

  public void completeWorkDayFlux() {
    this.sink.tryEmitComplete();
  }

  @SubscriptionMapping
  public Flux<Country> populationGrowing() {
    return this.sink.asFlux();
  }

}
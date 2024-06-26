package com.craftmanship.graphql.country.feature.graphql;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.Disposable;

@SpringBootTest
class CountrySubscriptionControllerTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(CountrySubscriptionControllerTest.class);

  @Autowired
  private CountrySubscriptionController countrySubscriptionController;

  @BeforeEach
  public void setUp() {
    LOGGER.info("Setting up CountrySubscriptionControllerTest");
  }

  @Test
  void testCountrySubscriptionController() throws InterruptedException {
    LOGGER.info("Testing CountrySubscriptionController");

    final CountDownLatch latch = new CountDownLatch(3);

    final Disposable subscribe = this.countrySubscriptionController.populationGrowing()
        .doOnSubscribe(subscription -> LOGGER.info("Subscribed"))
        .doOnNext(item -> {
          assertThat(item).isNotNull();
          LOGGER.info("Received item of class: {}", item.getClass().getName());
          LOGGER.info("Received: {}", item);
          assertThat(item.englishName()).isNotNull();
          latch.countDown();
        })
        .doOnError(error -> LOGGER.info("Encountered error: {}", error.toString()))
        .doOnComplete(() -> LOGGER.info("Flux completed"))
        .subscribe();

    latch.await();
    this.countrySubscriptionController.completeWorkDayFlux();
    LOGGER.info("Disposed: {}", subscribe.isDisposed());

  }

}
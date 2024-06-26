package com.craftmanship.graphql.country.feature.graphql;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureGraphQlTester
class CountryControllerTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(CountryControllerTest.class);

  @Autowired
  private GraphQlTester graphQlTester;

  @BeforeEach
  public void setUp() {
    LOGGER.info("Setting up CountryControllerTest");
  }

  @Test
  void testCountryController() {
    LOGGER.info("Testing CountryController");
    GraphQlTester.Response response = this.graphQlTester.documentName("country")
        .variable("id", "1")
        .execute();

    GraphQlTester.Path path = response.path("country.englishName");
    assertThat(path).isNotNull();
    assertThat(path.hasValue()).isNotNull();
    assertThat(path.entity(String.class).get()).isEqualTo("United Kingdom");
  }

}
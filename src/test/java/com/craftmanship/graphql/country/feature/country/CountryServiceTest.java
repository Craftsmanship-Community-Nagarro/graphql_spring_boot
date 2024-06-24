package com.craftmanship.graphql.country.feature.country;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class CountryServiceTest {

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private CountryRepository countryRepository;


  private CountryService countryService;

  @BeforeEach
  void setUp() {
    this.countryService = new CountryService(this.countryRepository);

    // Insert some data if needed, depends on the setting of the application-test.properties
    // Create a new country and call persist of the testEntityManager
    // Note: Configure Hibernate to create the schema automatically in the application-test.properties

    Country bolivia = new Country();
    bolivia.setEnglishName("Bolivia");
    this.testEntityManager.persist(bolivia);

    Country unitedKingdom = new Country();
    unitedKingdom.setEnglishName("United Kingdom");
    testEntityManager.persist(unitedKingdom);

    this.testEntityManager.flush();
  }

  @Test
  void findByEnglishName() {
    CountryDTO country = this.countryService.findByEnglishName("United Kingdom");

    assertThat(country).isNotNull();
    assertThat(country.englishName()).isEqualTo("United Kingdom");

  }

  @Test
  void findAllByOrderByIdDesc() {
    List<CountryDTO> allCountries = this.countryService.findAllByOrderByIdDesc();
    assertThat(allCountries).isNotEmpty().hasSize(2);
  }

  @Test
  void saveCountry() {
    Country germany = new Country();
    germany.setEnglishName("Germany");

    CountryDTO savedCountry = this.countryService.saveCountry(germany);

    assertThat(savedCountry).isNotNull();
    assertThat(savedCountry.englishName()).isEqualTo("Germany");
  }

  @Test
  void deleteCountry() {
    Country germany = new Country();
    germany.setEnglishName("Germany");

    CountryDTO savedCountry = this.countryService.saveCountry(germany);

    this.countryService.deleteCountry(savedCountry.id());

    List<CountryDTO> allCountries = this.countryService.findAllByOrderByIdDesc();
    assertThat(allCountries).isNotEmpty().doesNotContain(savedCountry);
  }
}
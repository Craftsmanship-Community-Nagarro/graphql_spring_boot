package com.craftmanship.graphql.country.feature.graphql;

import com.craftmanship.graphql.country.feature.country_language.CountryLanguageDTO;
import com.craftmanship.graphql.country.feature.country_language.CountryLanguageService;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CountryLanguageController {

  private final CountryLanguageService countryLanguageService;


  /**
   * All the countries where the language is spoken.
   * E.g. all the countries where English is spoken. Philippines, UK, USA, etc.
   *
   * @param englishName the english name
   * @return the country language
   */
  @QueryMapping
  public List<Country> countryLanguage(@Argument String englishName) {
    try {
      return getCountryLanguageById(englishName);
    } catch (Exception e) {
      return getEmptyCountryLanguageById(englishName);
    }
  }

  @QueryMapping
  public List<CountryLanguage> countryLanguages() {
    return this.countryLanguageService.findAllCountryLanguages()
        .entrySet()
        .stream()
        .map(entry -> new CountryLanguage.CountryLanguageBuilder()
            .englishName(entry.getKey())
            .id(entry.getValue().getFirst().id())
            .countries(entry.getValue().stream().map(clDTO -> new Country.CountryBuilder()
                .id(clDTO.countryId())
                .englishName(clDTO.countryLanguageName())
                .languages(Collections.emptyList())
                .build()).toList())
            .build())
        .toList();
  }

  private List<Country> getEmptyCountryLanguageById(String englishName) {
    return List.of(new Country.CountryBuilder()
        .id(Long.valueOf("-1"))
        .englishName("not found for the given english name: " + englishName)
        .build());
  }

  private List<Country> getCountryLanguageById(String englishName) {

    List<CountryLanguageDTO> countryLanguagesByEnglishName =
        this.countryLanguageService.findCountryLanguagesByEnglishName(englishName);

    return countryLanguagesByEnglishName.stream().map(clDTO -> new Country.CountryBuilder()
        .languages(List.of(new CountryLanguage.CountryLanguageBuilder()
            .englishName(clDTO.englishName())
            .id(clDTO.id())
            .build()))
        .id(clDTO.countryId())
        .englishName(clDTO.countryLanguageName())
        .build()).toList();
  }

}

package com.craftmanship.graphql.country.feature.graphql;

import com.craftmanship.graphql.country.feature.country.CountryDTO;
import com.craftmanship.graphql.country.feature.country.CountryService;
import com.craftmanship.graphql.country.feature.country_language.CountryLanguageDTO;
import com.craftmanship.graphql.country.feature.country_language.CountryLanguageService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CountryController {

  private final CountryService countryService;
  private final CountryLanguageService countryLanguageService;

  @QueryMapping
  public Country country(@Argument Long id) {
    try {
      return getCountry(id);
    } catch (Exception e) {
      return getEmptyCountry(id);
    }
  }

  private static Country getEmptyCountry(Long id) {
    return Country.builder().id(Long.valueOf("-1")).englishName("not found given id: " + id).build();
  }

  private Country getCountry(Long id) {
    final CountryDTO byId = this.countryService.findById(id);

    return Country.builder()
        .id(byId.id())
        .englishName(byId.englishName())
        .languages(getCountryLanguages(byId.countryLanguages(), byId))
        .build();
  }

  private List<CountryLanguage> getCountryLanguages(List<String> countryLanguages, CountryDTO byId) {
    return countryLanguages.stream().map(cl ->
            new CountryLanguage.CountryLanguageBuilder()
                .englishName(cl)
                .id(getByCountryIdAndEnglishName(byId, cl).id())
                .build())
        .toList();
  }

  private CountryLanguageDTO getByCountryIdAndEnglishName(CountryDTO byId, String cl) {
    return this.countryLanguageService.findByCountryIdAndEnglishName(byId.id(), cl);
  }
}
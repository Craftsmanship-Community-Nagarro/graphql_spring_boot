package com.craftmanship.graphql.country.feature.graphql;

import com.craftmanship.graphql.country.feature.country.CountryDTO;
import com.craftmanship.graphql.country.feature.country.CountryService;
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

    CountryDTO byId = this.countryService.findById(id);
    List<String> countryLanguages = byId.countryLanguages();

    return Country.builder()
        .id(byId.id())
        .englishName(byId.englishName())
        .languages(

            countryLanguages.stream().map(cl -> new CountryLanguage.CountryLanguageBuilder().englishName(cl).build()).toList()

        )
        .build();
  }


}

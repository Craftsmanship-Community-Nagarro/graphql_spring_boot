package com.craftmanship.graphql.country.feature.graphql;

import com.craftmanship.graphql.country.feature.country.CountryDTO;
import com.craftmanship.graphql.country.feature.country.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

/**
 * The type Country mutation controller.
 * All the mutations for the country entity.
 *
 * @Controller this annotation is used to mark any java class as a controller class.
 * @AllArgsConstructor this annotation generates a constructor with one parameter for each field in your class.
 */
@Controller
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CountryMutationController {

  private final CountryService countryService;

  /**
   * Update country name.
   *
   * @param oldName the old name
   * @param newName the new name
   * @return the country
   */
  @MutationMapping
  public Country updateCountryName(@Argument final String oldName,
                                   @Argument final String newName) {
    try {
      return mapFromDTO(this.countryService.updateCountry(oldName, newName));
    } catch (Exception e) {
      return getEmptyCountry(oldName);
    }
  }

  private Country getEmptyCountry(final String oldName) {
    return new Country.CountryBuilder()
        .englishName(oldName + " not found in database")
        .id(-1L)
        .build();
  }

  private static Country mapFromDTO(final CountryDTO countryDTO) {
    return new Country.CountryBuilder()
        .id(countryDTO.id())
        .englishName(countryDTO.englishName())
        .build();
  }

}
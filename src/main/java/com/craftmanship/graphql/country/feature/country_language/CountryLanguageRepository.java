package com.craftmanship.graphql.country.feature.country_language;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, Long> {

  /**
   * Find all country languages by country id.
   *
   * @param countryId the country id
   * @return the list
   */
  @Query(" from country_language cl where cl.country.id = :countryId")
  List<CountryLanguage> findAllByCountryId(@Param("countryId") Long countryId);

  /**
   * Find country languages by english name.
   *
   * @param englishName the english name
   * @return the list
   */
  @Query(" from country_language cl where cl.englishName = :englishName")
  List<CountryLanguage> findCountryLanguagesByEnglishName(String englishName);

  /**
   * Find all country languages order by language name asc.
   *
   * @return the list
   */
  @Query(" from country_language cl order by cl.englishName asc")
  List<CountryLanguage> findAllOrderByLanguageNameAsc();
}

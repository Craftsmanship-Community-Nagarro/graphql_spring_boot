package com.craftmanship.graphql.country.feature.country;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends JpaRepository<Country, Long> {

  /**
   * Find country by english name.
   *
   * @param englishName the english name
   * @return the country
   */
  @Query(" from country c where c.englishName = :englishName")
  Country findByEnglishName(@Param("englishName") String englishName);

  /**
   * Find all countries order by id desc.
   *
   * @return the list
   */
  @Query(" from country c ORDER BY c.id desc")
  List<Country> findAllByOrderByIdDesc();

}

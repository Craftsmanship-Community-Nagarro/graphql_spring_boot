package com.craftmanship.graphql.country.feature.country_language;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CountryLanguageRestController {
  private final CountryLanguageService countryLanguageService;

  @GetMapping("/languages")
  public ResponseEntity<Map<String, List<CountryLanguageDTO>>> getAllLanguages() {
    return ResponseEntity.ok(this.countryLanguageService.findAllCountryLanguages());
  }

  @GetMapping("/languages/{countryId}")
  public ResponseEntity<Map<String, List<CountryLanguageDTO>>> getLanguagesByCountryId(
      @PathVariable(value = "countryId") final Long countryId) {
    return ResponseEntity.ok(this.countryLanguageService.findAllByCountryId(countryId));
  }
}

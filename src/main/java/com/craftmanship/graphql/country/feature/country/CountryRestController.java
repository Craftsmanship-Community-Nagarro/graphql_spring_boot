package com.craftmanship.graphql.country.feature.country;


import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

@AllArgsConstructor(onConstructor_ = @Autowired)
public class CountryRestController {

  private final CountryService countryService;

  @GetMapping("/countries")
  public ResponseEntity<List<CountryDTO>> getAllCountries() {
    return ResponseEntity.ok(this.countryService.findAllByOrderByIdDesc());
  }

  @GetMapping("/countries/{name}")
  public ResponseEntity<CountryDTO> getCountryById(@PathVariable(value = "name") String name) {
    return ResponseEntity.ok(this.countryService.findByEnglishName(name));
  }

  @DeleteMapping("/countries/{id}")
  public ResponseEntity<Void> deleteCountry(@PathVariable(value = "id") Long id) {
    this.countryService.deleteCountry(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/countries/{oldName}/{newName}")
  public ResponseEntity<CountryDTO> updateCountry(@PathVariable(value = "oldName") String oldName,
                                                  @PathVariable(value = "newName") String newName) {
    return ResponseEntity.ok(this.countryService.updateCountry(oldName, newName));
  }
}
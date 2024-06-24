package com.craftmanship.graphql.country.feature.country;

import static io.micrometer.common.util.StringUtils.isBlank;

import com.craftmanship.graphql.country.feature.country_language.CountryLanguage;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CountryService {

  private static final String COUNTRY_IS_NULL = "Country is null";
  private final CountryRepository countryRepository;

  public CountryDTO findByEnglishName(final String englishName) {

    if (isBlank(englishName)) {
      throw new InvalidCountryException("English name is blank");
    }

    return mapToDTO(this.countryRepository.findByEnglishName(englishName));
  }

  public List<CountryDTO> findAllByOrderByIdDesc() {
    return this.countryRepository.findAllByOrderByIdDesc().stream().map(this::mapToDTO).toList();
  }

  public CountryDTO saveCountry(final Country country) {

    if (Objects.isNull(country)) {
      throw new InvalidCountryException(COUNTRY_IS_NULL);
    }

    if (StringUtils.isBlank(country.getEnglishName())) {
      throw new InvalidCountryException("English name is blank");
    }

    return mapToDTO(this.countryRepository.save(country));
  }

  private CountryDTO mapToDTO(final Country country) {

    if (Objects.isNull(country)) {
      throw new InvalidCountryException(COUNTRY_IS_NULL);
    }

    return new CountryDTO.CountryDTOBuilder()
        .englishName(country.getEnglishName())
        .id(country.getId())
        .countryLanguages(country.getLanguages().stream().map(CountryLanguage::getEnglishName).toList())
        .build();
  }

  public void deleteCountry(final Long id) {

    if (Objects.isNull(id)) {
      throw new InvalidCountryException("Id is null");
    }

    this.countryRepository.deleteById(id);
  }

  public CountryDTO updateCountry(String oldName, String newName) {
    final Country country = this.countryRepository.findByEnglishName(oldName);

    if (Objects.isNull(country)) {
      throw new InvalidCountryException(COUNTRY_IS_NULL);
    }

    country.setEnglishName(newName);
    return mapToDTO(this.countryRepository.save(country));
  }
}
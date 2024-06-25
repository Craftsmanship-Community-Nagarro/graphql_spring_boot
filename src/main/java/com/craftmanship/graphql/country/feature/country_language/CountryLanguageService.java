package com.craftmanship.graphql.country.feature.country_language;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CountryLanguageService {
  private final CountryLanguageRepository countryLanguageRepository;

  public Map<String, List<CountryLanguageDTO>> findAllByCountryId(final Long countryId) {
    if (Objects.isNull(countryId)) {
      throw new InvalidCountryLanguageException("Country id is null");
    }

    //Convert stream to map where the key is the english name and the value is the list of DTOs
    return this.countryLanguageRepository
        .findAllByCountryId(countryId)
        .stream()
        .collect(groupByTheName());
  }

  public CountryLanguageDTO findByCountryIdAndEnglishName(final Long countryId, final String englishName) {
    if (Objects.isNull(countryId) || Objects.isNull(englishName)) {
      throw new InvalidCountryLanguageException("Country id or english name is null");
    }

    Optional<CountryLanguage> byCountryIdAndEnglishName = this.countryLanguageRepository
        .findByCountryIdAndEnglishName(countryId, englishName);

    return mapToDTO(byCountryIdAndEnglishName.orElseThrow(() ->
        new InvalidCountryLanguageException(
            "Country language not found by country id and english name: " + countryId + " " + englishName)));
  }

  public Map<String, List<CountryLanguageDTO>> findAllCountryLanguages() {
    return this.countryLanguageRepository.findAllOrderByLanguageNameAsc()
        .stream()
        .collect(groupByTheName());
  }

  public List<CountryLanguageDTO> findCountryLanguagesByEnglishName(final String englishName) {

    if (Objects.isNull(englishName)) {
      throw new InvalidCountryLanguageException("English name is null");
    }

    return this.countryLanguageRepository.findCountryLanguagesByEnglishName(englishName)
        .stream()
        .map(this::mapToDTO)
        .toList();
  }

  private CountryLanguageDTO mapToDTO(final CountryLanguage countryLanguage) {

    if (Objects.isNull(countryLanguage)) {
      throw new InvalidCountryLanguageException("Country language is null");
    }

    return new CountryLanguageDTO
        .CountryLanguageDTOBuilder()
        .englishName(countryLanguage.getEnglishName())
        .countryLanguageName(countryLanguage.getCountry().getEnglishName())
        .id(countryLanguage.getId())
        .countryId(countryLanguage.getCountry().getId())
        .build();
  }

  private Collector<CountryLanguage, ?, Map<String, List<CountryLanguageDTO>>> groupByTheName() {
    return Collectors.groupingBy(
        CountryLanguage::getEnglishName,
        Collectors.mapping(this::mapToDTO, Collectors.toList())
    );
  }

}

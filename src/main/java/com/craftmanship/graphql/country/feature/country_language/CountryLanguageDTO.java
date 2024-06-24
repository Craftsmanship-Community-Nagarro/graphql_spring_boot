package com.craftmanship.graphql.country.feature.country_language;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record CountryLanguageDTO(Long id,
                                 @JsonIgnore String englishName,
                                 String countryLanguageName,
                                 Long countryId) {
}

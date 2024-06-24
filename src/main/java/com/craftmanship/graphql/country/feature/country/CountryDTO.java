package com.craftmanship.graphql.country.feature.country;

import java.util.List;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record CountryDTO(Long id, String englishName, List<String> countryLanguages) {
}

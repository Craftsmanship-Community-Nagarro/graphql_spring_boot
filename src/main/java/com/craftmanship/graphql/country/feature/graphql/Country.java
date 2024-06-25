package com.craftmanship.graphql.country.feature.graphql;

import java.util.List;
import lombok.Builder;
import lombok.With;

@With
@Builder
public record Country(Long id, String englishName, List<CountryLanguage> languages) {
}

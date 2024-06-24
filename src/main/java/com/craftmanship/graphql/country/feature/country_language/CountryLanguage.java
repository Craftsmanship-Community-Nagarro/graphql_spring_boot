package com.craftmanship.graphql.country.feature.country_language;

import com.craftmanship.graphql.country.feature.common.BaseEntity;
import com.craftmanship.graphql.country.feature.country.Country;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.io.Serial;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity(name = "country_language")
@Table(name = "country_language", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ID_COUNTRY", "ENGLISH_NAME"})
})
@Getter
@Setter
public class CountryLanguage extends BaseEntity<Long> {

  @Serial
  private static final long serialVersionUID = 127766990110516908L;

  @ManyToOne
  @JoinColumn(name = "id_country")
  private Country country;

  @Column(
      name = "english_name",
      nullable = false,
      length = 100
  )
  private String englishName;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CountryLanguage that = (CountryLanguage) o;

    return new EqualsBuilder()
        .append(this.getCountry().getId(), that.getCountry().getId())
        .append(this.getEnglishName(), that.getEnglishName())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(this.getCountry().getId())
        .append(this.getEnglishName())
        .toHashCode();
  }

  @Override
  public Long getId() {
    return this.id;
  }
}

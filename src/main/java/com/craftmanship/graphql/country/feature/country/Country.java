package com.craftmanship.graphql.country.feature.country;

import com.craftmanship.graphql.country.feature.common.BaseEntity;
import com.craftmanship.graphql.country.feature.country_language.CountryLanguage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.io.Serial;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


@Entity(name = "country")
@Table(name = "country")
@Getter
@Setter
public class Country extends BaseEntity<Long> {
  @Serial
  private static final long serialVersionUID;

  static {
    serialVersionUID = -6132720120419588672L;
  }

  @Column(name = "english_name", nullable = false, length = 100, unique = true)
  private String englishName;

  @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("englishName ASC")
  private Set<CountryLanguage> languages;

  @Override
  public Long getId() {
    return this.id;
  }

  @Override
  public boolean equals(Object thatObject) {
    if (this == thatObject) {
      return true;
    }

    if (thatObject instanceof Country that) {
      return new EqualsBuilder().append(this.getId(), that.getId()).append(this.getEnglishName(), that.getEnglishName())
          .isEquals();
    }

    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(this.getId()).append(this.getEnglishName())
        .toHashCode();
  }

  @Override
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this);
    builder.append("id", this.getId());
    builder.append("englishName", this.getEnglishName());
    return builder.toString();
  }
}

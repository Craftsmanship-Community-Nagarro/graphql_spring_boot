package com.craftmanship.graphql.country.feature.graphql;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CountryController {

  @QueryMapping
  public Country country(@Argument Long id) {
    return Country.builder()
        .id(id)
        .englishName("hello world")
        .build();
  }

}

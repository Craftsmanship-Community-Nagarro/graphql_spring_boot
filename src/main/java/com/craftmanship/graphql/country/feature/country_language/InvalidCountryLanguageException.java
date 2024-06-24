package com.craftmanship.graphql.country.feature.country_language;

public class InvalidCountryLanguageException extends RuntimeException {
  public InvalidCountryLanguageException() {
    super();
  }

  public InvalidCountryLanguageException(String message) {
    super(message);
  }

  public InvalidCountryLanguageException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidCountryLanguageException(Throwable cause) {
    super(cause);
  }

  protected InvalidCountryLanguageException(String message, Throwable cause, boolean enableSuppression,
                                            boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

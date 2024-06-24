package com.craftmanship.graphql.country.feature.country;

public class InvalidCountryException extends RuntimeException {

  public InvalidCountryException() {
    super();
  }

  public InvalidCountryException(String message) {
    super(message);
  }

  public InvalidCountryException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidCountryException(Throwable cause) {
    super(cause);
  }

  protected InvalidCountryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

package org.szymon.publicationservice.Exceptions;

public class PublicationNotFoundException extends RuntimeException {
  public PublicationNotFoundException(String message) {
    super(message);
  }
}

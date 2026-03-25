package org.szymon.publication.Exceptions;

public class PublicationNotFoundException extends RuntimeException {
  public PublicationNotFoundException(String message) {
    super(message);
  }
}

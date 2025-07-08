package org.example.project.Exceptions;

public class PublicationNotFoundException extends RuntimeException {
  public PublicationNotFoundException(String message) {
    super(message);
  }
}

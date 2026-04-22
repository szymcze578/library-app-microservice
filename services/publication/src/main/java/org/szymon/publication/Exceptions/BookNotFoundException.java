package org.szymon.publication.Exceptions;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(String isbn) {
    super("Book with ISBN '" + isbn + "' not found");
  }
}

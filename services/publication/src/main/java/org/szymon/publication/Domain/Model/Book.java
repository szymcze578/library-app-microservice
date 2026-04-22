package org.szymon.publication.Domain.Model;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("BOOK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Book extends Publication {

    @NotBlank(message = "Author must not be blank")
    @Size(max = 200)
    @Column(length = 200)
    private String author;

    @NotNull(message = "Number of pages is required")
    @Positive(message = "Pages must be positive")
    private Integer pages;

    /**
     * ISBN is nullable to accommodate older books, self-published works,
     * and internal acquisitions without an official ISBN.
     * When present, it must be unique and validly formatted (ISBN-10 or ISBN-13).
     */
    @Pattern(
            regexp = "^(?:\\d{9}[\\dX]|\\d{13})$",
            message = "ISBN must be valid ISBN-10 (10 chars, last may be X) or ISBN-13 (13 digits)"
    )
    @Column(unique = true, length = 13)
    private String isbn;
}

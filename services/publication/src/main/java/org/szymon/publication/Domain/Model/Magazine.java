package org.szymon.publication.Domain.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.szymon.publication.Domain.Enums.Periodicity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("MAGAZINE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Magazine extends Publication {

    @NotNull(message = "Issue date is required")
    @Column(name = "issue_date")
    private LocalDate issueDate;

    @NotBlank(message = "Issue number must not be blank")
    @Size(max = 50)
    @Column(name = "issue_number", length = 50)
    private String issueNumber;

    @NotNull(message = "Periodicity is required")
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Periodicity periodicity;

    /**
     * ISSN — the magazine equivalent of ISBN. 8 digits with a dash, last may be X.
     * Nullable for the same reasons as ISBN on Book.
     */
    @Pattern(
            regexp = "^\\d{4}-\\d{3}[\\dX]$",
            message = "ISSN must match format NNNN-NNNX (e.g. 1234-5678)"
    )
    @Column(unique = true, length = 9)
    private String issn;
}

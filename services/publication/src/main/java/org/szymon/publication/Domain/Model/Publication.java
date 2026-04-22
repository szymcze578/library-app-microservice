package org.szymon.publication.Domain.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.Year;

@Entity
@Table(name = "publication", indexes = {
        @Index(name = "idx_publication_title", columnList = "title"),
        @Index(name = "idx_publication_type", columnList = "type")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title must not be blank")
    @Size(max = 255, message = "Title must be at most 255 characters")
    @Column(nullable = false, length = 255)
    private String title;

    @NotNull(message = "Publication year is required")
    @Min(value = 1000, message = "Publication year must be after 1000")
    @Column(name = "publication_year", nullable = false)
    private Integer publicationYear;

    @NotBlank(message = "Publisher must not be blank")
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String publisher;

    @NotBlank(message = "Language must not be blank")
    @Size(min = 2, max = 10, message = "Language must be an ISO code")
    @Column(nullable = false, length = 10)
    private String language;

    @Size(max = 100)
    @Column(length = 100)
    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    /**
     * Storage key (not URL) for the cover image in object storage.
     * Nullable — publications don't require covers.
     * The full URL is constructed by the frontend from the service endpoint.
     */
    @Size(max = 255)
    @Column(name = "cover_image_key", length = 255)
    private String coverImageKey;

    /**
     * Domain-level validation: publication year should not be in the future.
     * Kept as a method rather than annotation because "current year" is dynamic.
     */
    @AssertTrue(message = "Publication year cannot be in the future")
    public boolean isPublicationYearValid() {
        if (publicationYear == null) return true;
        return publicationYear <= Year.now().getValue();
    }
}

package org.szymon.publication.Domain.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.szymon.publication.Domain.Enums.AcquisitionType;
import org.szymon.publication.Domain.Enums.CatalogCondition;
import org.szymon.publication.Domain.Enums.CatalogStatus;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "publication_catalog", indexes = {
        @Index(name = "idx_catalog_publication", columnList = "publication_id"),
        @Index(name = "idx_catalog_status", columnList = "status"),
        @Index(name = "idx_catalog_inventory_code", columnList = "inventory_code", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicationCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "publication_id", nullable = false)
    private Long publicationId;

    @NotBlank
    @Size(max = 30)
    @Column(name = "inventory_code", nullable = false, unique = true, length = 30)
    private String inventoryCode;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private CatalogStatus status = CatalogStatus.AVAILABLE;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private CatalogCondition condition = CatalogCondition.GOOD;

    @Size(max = 50)
    @Column(name = "shelf_location", length = 50)
    private String shelfLocation;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "acquisition_type", nullable = false, length = 20)
    @Builder.Default
    private AcquisitionType acquisitionType = AcquisitionType.BORROW_ONLY;

    @DecimalMin(value = "0.00", inclusive = false, message = "Price must be positive when set")
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    /**
     * Domain behavior: only copies in AVAILABLE status can be borrowed.
     */
    public boolean isBorrowable() {
        return status == CatalogStatus.AVAILABLE
                && acquisitionType != AcquisitionType.SELL_ONLY;
    }

    public void markBorrowed() {
        if (!isBorrowable()) {
            throw new IllegalStateException(
                    "Copy " + inventoryCode + " is not borrowable; status=" + status
            );
        }
        this.status = CatalogStatus.BORROWED;
    }

    public void markAvailable() {
        this.status = CatalogStatus.AVAILABLE;
    }
}

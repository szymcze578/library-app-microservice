package org.szymon.user.Domain.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Entity
@Table(name = "addresses")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "City must not be blank")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String city;

    @NotBlank(message = "Street must not be blank")
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String street;

    @NotBlank(message = "House number must not be blank")
    @Size(max = 20)
    @Column(name = "home", nullable = false, length = 20)
    private String home;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<User> users;
}

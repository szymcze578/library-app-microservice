package org.szymon.user.Domain.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String city;
    private String street;
    private String home;
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<User> users;
}

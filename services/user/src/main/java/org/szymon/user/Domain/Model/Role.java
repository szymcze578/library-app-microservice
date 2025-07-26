package org.szymon.user.Domain.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private RoleType type;
    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<>();
}

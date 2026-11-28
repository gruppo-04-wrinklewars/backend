package com.wrinklewars.entities;

import jakarta.persistence.*;
import java.time.Instant;


@org.hibernate.annotations.Check(constraints = "(btrim(name) <> '')")
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    // Role-level provisioning policy, not an account record.
    private Boolean bootstrapSuppressed;
    public boolean isBootstrapSuppressed() { return Boolean.TRUE.equals(bootstrapSuppressed); }

    protected Role() {}

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

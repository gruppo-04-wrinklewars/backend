package com.wrinklewars.entities;

import jakarta.persistence.*;
import java.time.Instant;


@org.hibernate.annotations.Check(constraints = "(btrim(name) <> '')")
@Entity
@Table(name = "factions")
public class FactionCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    protected FactionCatalog() {}

    public FactionCatalog(String name) {
        this.name = name;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

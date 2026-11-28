package com.wrinklewars.entities;

import jakarta.persistence.*;
import java.time.Instant;


@org.hibernate.annotations.Check(constraints = "(btrim(name) <> '')")
@Entity
@Table(name = "note_types")
public class NoteType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 40, unique = true)
    private String name;

    public NoteType() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

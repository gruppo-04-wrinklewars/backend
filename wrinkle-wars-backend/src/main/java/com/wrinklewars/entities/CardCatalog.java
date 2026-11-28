package com.wrinklewars.entities;

import jakarta.persistence.*;
import java.time.Instant;

@org.hibernate.annotations.Check(constraints = "(btrim(name) <> '') AND (nickname IS NULL OR btrim(nickname) <> '') AND ((note_type_id IS NULL AND note IS NULL) OR (note_type_id IS NOT NULL AND note IS NOT NULL AND btrim(note) <> ''))")
@Entity
@Table(name = "cards", indexes = {@Index(name = "idx_cards_type", columnList = "card_type_id"), @Index(name = "idx_cards_type_note", columnList = "note_type_id")})
public class CardCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 150)
    private String nickname;

    @Column(columnDefinition = "text")
    private String description;

    @Column(length = 500)
    private String imageUrl;
    @Column(name = "card_type_id", nullable = false)
    private Integer cardTypeId;
    @Column(name = "note_type_id")
    private Integer noteTypeId;

    @Column(columnDefinition = "text")
    private String note;
    @Column(nullable = false)
    private Instant createdAt;
    @Column(nullable = false)
    private Instant updatedAt;

    protected CardCatalog() {}

    public CardCatalog(String name, String nickname, String description,
                         String imageUrl, Integer cardTypeId, Integer noteTypeId, String note) {
        this.name = name;
        this.nickname = nickname;
        this.description = description;
        this.imageUrl = imageUrl;
        this.cardTypeId = cardTypeId;
        this.noteTypeId = noteTypeId;
        this.note = note;
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Integer getCardTypeId() { return cardTypeId; }
    public void setCardTypeId(Integer cardTypeId) { this.cardTypeId = cardTypeId; }
    public Integer getNoteTypeId() { return noteTypeId; }
    public void setNoteTypeId(Integer noteTypeId) { this.noteTypeId = noteTypeId; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_type_id", referencedColumnName = "id", insertable = false, updatable = false,
       foreignKey = @ForeignKey(name = "fk_cards_card_type_id"))
    private CardType cardTypeReference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "note_type_id", referencedColumnName = "id", insertable = false, updatable = false,
       foreignKey = @ForeignKey(name = "fk_cards_note_type_id"))
    private NoteType noteTypeReference;

}

package com.wrinklewars.entities;

import jakarta.persistence.*;
import java.time.Instant;


@org.hibernate.annotations.Check(constraints = "(btrim(name) <> '') AND (max_copies_per_card > 0) AND (max_cards_per_deck IS NULL OR max_cards_per_deck > 0) AND (atk_min > 0 AND atk_max >= atk_min) AND (hp_min > 0 AND hp_max >= hp_min)")
@Entity
@Table(name = "rarities")
public class RarityCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;
    @Column(nullable = false)
    private Short maxCopiesPerCard;
    private Short maxCardsPerDeck;
    @Column(nullable = false)
    private Short atkMin;
    @Column(nullable = false)
    private Short atkMax;
    @Column(nullable = false)
    private Short hpMin;
    @Column(nullable = false)
    private Short hpMax;

    protected RarityCatalog() {}

    public RarityCatalog(String name, short maxCopiesPerCard, Short maxCardsPerDeck,
                          short atkMin, short atkMax, short hpMin, short hpMax) {
        this.name = name;
        this.maxCopiesPerCard = maxCopiesPerCard;
        this.maxCardsPerDeck = maxCardsPerDeck;
        this.atkMin = atkMin;
        this.atkMax = atkMax;
        this.hpMin = hpMin;
        this.hpMax = hpMax;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Short getMaxCopiesPerCard() { return maxCopiesPerCard; }
    public void setMaxCopiesPerCard(Short maxCopiesPerCard) { this.maxCopiesPerCard = maxCopiesPerCard; }
    public Short getMaxCardsPerDeck() { return maxCardsPerDeck; }
    public void setMaxCardsPerDeck(Short maxCardsPerDeck) { this.maxCardsPerDeck = maxCardsPerDeck; }
    public Short getAtkMin() { return atkMin; }
    public void setAtkMin(Short atkMin) { this.atkMin = atkMin; }
    public Short getAtkMax() { return atkMax; }
    public void setAtkMax(Short atkMax) { this.atkMax = atkMax; }
    public Short getHpMin() { return hpMin; }
    public void setHpMin(Short hpMin) { this.hpMin = hpMin; }
    public Short getHpMax() { return hpMax; }
    public void setHpMax(Short hpMax) { this.hpMax = hpMax; }
}

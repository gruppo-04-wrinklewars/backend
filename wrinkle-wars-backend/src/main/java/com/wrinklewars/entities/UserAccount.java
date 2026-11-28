package com.wrinklewars.entities;

import jakarta.persistence.*;
import java.time.Instant;

@org.hibernate.annotations.Check(constraints = "(btrim(username) <> '') AND (btrim(email) <> '') AND (btrim(password_hash) <> '') AND (credit_balance >= 0) AND (daily_streak >= 0) AND (version >= 0) AND ((active = TRUE AND deactivated_at IS NULL) OR (active = FALSE AND deactivated_at IS NOT NULL))")
@Entity
@Table(name = "users", indexes = {@Index(name = "idx_users_role", columnList = "role_id")})
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 50, unique = true)
    private String username;
    @Column(length = 80)
    private String name;
    @Column(length = 80)
    private String surname;
    @org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.VARBINARY)
    @Column(name = "avatar_data", columnDefinition = "bytea")
    private byte[] avatarData;
    @Column(name = "avatar_type", length = 30)
    private String avatarType;
    private Instant avatarUpdatedAt;
    @Column(nullable = false, length = 254, unique = true)
    private String email;
    @Column(nullable = false, length = 255)
    private String passwordHash;
    @Column(name = "role_id", nullable = false)
    private Integer roleId;
    @Column(nullable = false)
    private Integer creditBalance;
    @Column(nullable = false)
    private boolean active = true;
    private Instant deactivatedAt;
    /** Legacy V8.1.0 migration marker only; new deletions remove the row. */
    private Instant deletedAt;
    private Boolean gameInitialized;
    @Column(length=50)
    private String bootstrapIdentity;
    public String getBootstrapIdentity() { return bootstrapIdentity; }
    public void setBootstrapIdentity(String value) { bootstrapIdentity=value; }
    public boolean isDeleted() { return deletedAt != null; }
    public Instant getDeletedAt() { return deletedAt; }
    public void setDeletedAt(Instant value) { deletedAt = value; }
    public boolean isGameInitialized() { return Boolean.TRUE.equals(gameInitialized); }
    public void setGameInitialized(boolean value) { gameInitialized = value; }
    public String historicalName() { return isDeleted() ? "Account eliminato" : username; }

    /** Requires staff profile setup and a personal password at first sign-in. */
    @Column(name="staff_setup_required")
    private Boolean staffSetupRequired = false;
    @Version
    @Column(nullable = false)
    private Long version;
    @Column(nullable = false)
    private Integer dailyStreak;
    private Instant lastLoginAt;
    @Column(nullable = false)
    private Instant createdAt;
    @Column(nullable = false)
    private Instant updatedAt;

    protected UserAccount() {}

    public UserAccount(String username, String email, String passwordHash, Integer roleId) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.roleId = roleId;
        this.creditBalance = 0;
        this.dailyStreak = 0;
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    public byte[] getAvatarData() { return avatarData; }
    public void setAvatarData(byte[] data) { this.avatarData = data; }
    public String getAvatarType() { return avatarType; }
    public void setAvatarType(String type) { this.avatarType = type; }
    public Instant getAvatarUpdatedAt() { return avatarUpdatedAt; }
    public void setAvatarUpdatedAt(Instant when) { this.avatarUpdatedAt = when; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public Instant getDeactivatedAt() { return deactivatedAt; }
    public void setDeactivatedAt(Instant value) { this.deactivatedAt = value; }
    public boolean isStaffSetupRequired() { return Boolean.TRUE.equals(staffSetupRequired); }
    /** Read the nullable staff-onboarding flag. */
    public Boolean getStaffSetupFlag() { return staffSetupRequired; }
    public void setStaffSetupRequired(boolean required) { this.staffSetupRequired = required; }
    public Long getVersion() { return version; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public Integer getRoleId() { return roleId; }
    public void setRoleId(Integer roleId) { this.roleId = roleId; }
    public Integer getCreditBalance() { return creditBalance; }
    public void setCreditBalance(Integer creditBalance) { this.creditBalance = creditBalance; }
    public Integer getDailyStreak() { return dailyStreak; }
    public void setDailyStreak(Integer dailyStreak) { this.dailyStreak = dailyStreak; }
    public Instant getLastLoginAt() { return lastLoginAt; }
    public void setLastLoginAt(Instant lastLoginAt) { this.lastLoginAt = lastLoginAt; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false,
       foreignKey = @ForeignKey(name = "fk_users_role_id"))
    private Role linkRoleId;

}

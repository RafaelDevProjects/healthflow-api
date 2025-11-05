package com.healthflow.healthflow_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "health_activities")
public class HealthActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull(message = "Tipo de atividade é obrigatório")
    @Column(name = "activity_type", nullable = false, length = 20)
    private String activityType;

    @NotNull(message = "Data é obrigatória")
    @Column(nullable = false)
    private LocalDate date;

    @Min(value = 0, message = "Valor não pode ser negativo")
    @Column(name = "activity_value", nullable = false) // Mudei de 'value' para 'activity_value'
    private Double activityValue;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Construtores
    public HealthActivity() {
        this.createdAt = LocalDateTime.now();
    }

    public HealthActivity(User user, String activityType, LocalDate date, Double activityValue) {
        this();
        this.user = user;
        this.activityType = activityType;
        this.date = date;
        this.activityValue = activityValue;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getActivityType() { return activityType; }
    public void setActivityType(String activityType) { this.activityType = activityType; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Double getActivityValue() { return activityValue; }
    public void setActivityValue(Double activityValue) { this.activityValue = activityValue; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
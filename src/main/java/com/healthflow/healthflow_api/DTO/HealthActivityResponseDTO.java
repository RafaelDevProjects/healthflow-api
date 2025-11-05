package com.healthflow.healthflow_api.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "DTO para resposta de atividades de saúde")
public class HealthActivityResponseDTO {

    @Schema(description = "ID da atividade", example = "1")
    private Long id;

    @Schema(description = "ID do usuário", example = "1")
    private Long userId;

    @Schema(description = "Nome do usuário", example = "João Silva")
    private String userName;

    @Schema(description = "Tipo de atividade", example = "EXERCISE")
    private String activityType;

    @Schema(description = "Data da atividade", example = "2025-11-04")
    private LocalDate date;

    @Schema(description = "Valor da atividade", example = "30.0")
    private Double activityValue;

    @Schema(description = "Data de criação do registro", example = "2025-11-04T21:50:00.123")
    private LocalDateTime createdAt;

    // Construtores, Getters e Setters (mantenha os existentes)
    public HealthActivityResponseDTO() {}

    public HealthActivityResponseDTO(Long id, Long userId, String userName,
                                     String activityType, LocalDate date,
                                     Double activityValue, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.activityType = activityType;
        this.date = date;
        this.activityValue = activityValue;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getActivityType() { return activityType; }
    public void setActivityType(String activityType) { this.activityType = activityType; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Double getActivityValue() { return activityValue; }
    public void setActivityValue(Double activityValue) { this.activityValue = activityValue; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

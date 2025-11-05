package com.healthflow.healthflow_api.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


@Schema(description = "DTO para criação de atividades de saúde")
public class HealthActivityDTO {

    private Long id;

    @NotNull(message = "ID do usuário é obrigatório")
    @Schema(description = "ID do usuário", example = "1")
    private Long userId;

    @NotNull(message = "Tipo de atividade é obrigatório")
    @Schema(description = "Tipo de atividade",
            example = "EXERCISE",
            allowableValues = {"EXERCISE", "WATER", "SLEEP", "MEDITATION", "WALKING"})
    private String activityType;

    @NotNull(message = "Data é obrigatória")
    @Schema(description = "Data da atividade", example = "2025-11-04")
    private LocalDate date;

    @NotNull(message = "Valor é obrigatório")
    @Min(value = 0, message = "Valor não pode ser negativo")
    @Schema(description = "Valor da atividade (minutos, litros, horas, etc.)", example = "30.0")
    private Double activityValue;

    // Construtores, Getters e Setters (mantenha os existentes)
    public HealthActivityDTO() {}

    public HealthActivityDTO(Long userId, String activityType, LocalDate date, Double activityValue) {
        this.userId = userId;
        this.activityType = activityType;
        this.date = date;
        this.activityValue = activityValue;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getActivityType() { return activityType; }
    public void setActivityType(String activityType) { this.activityType = activityType; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Double getActivityValue() { return activityValue; }
    public void setActivityValue(Double activityValue) { this.activityValue = activityValue; }
}
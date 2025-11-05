package com.healthflow.healthflow_api.controller;


import com.healthflow.healthflow_api.DTO.*;
import com.healthflow.healthflow_api.model.HealthActivity;
import com.healthflow.healthflow_api.service.HealthActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "*")
@Tag(name = "Atividades de Saúde", description = "API para gerenciamento de atividades de saúde")
public class HealthActivityController {

    @Autowired
    private HealthActivityService activityService;

    @Operation(summary = "Listar atividades do usuário", description = "Retorna todas as atividades de saúde de um usuário específico")
    @ApiResponse(responseCode = "200", description = "Lista de atividades retornada com sucesso")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HealthActivityResponseDTO>> getActivitiesByUser(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long userId) {
        List<HealthActivityResponseDTO> activities = activityService.findAllByUserId(userId);
        return ResponseEntity.ok(activities);
    }

    @Operation(summary = "Buscar atividade por ID", description = "Retorna uma atividade específica baseada no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atividade encontrada"),
            @ApiResponse(responseCode = "404", description = "Atividade não encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<HealthActivityResponseDTO> getActivityById(
            @Parameter(description = "ID da atividade", example = "1")
            @PathVariable Long id) {
        Optional<HealthActivity> activity = activityService.findById(id);
        if (activity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        HealthActivityResponseDTO dto = activityService.convertEntityToDTO(activity.get());
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Criar nova atividade", description = "Registra uma nova atividade de saúde para um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Atividade criada com sucesso",
                    content = @Content(schema = @Schema(implementation = HealthActivityResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário não encontrado", content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> createActivity(
            @Parameter(description = "Dados da atividade para criação")
            @Valid @RequestBody HealthActivityDTO activityDTO) {
        try {
            HealthActivityResponseDTO savedActivity = activityService.save(activityDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedActivity);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @Operation(summary = "Excluir atividade", description = "Remove uma atividade de saúde do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Atividade excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Atividade não encontrada", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(
            @Parameter(description = "ID da atividade", example = "1")
            @PathVariable Long id) {
        if (activityService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        activityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obter estatísticas", description = "Retorna estatísticas das atividades do usuário a partir de uma data específica")
    @ApiResponse(responseCode = "200", description = "Estatísticas calculadas com sucesso")
    @GetMapping("/user/{userId}/statistics")
    public ResponseEntity<List<Object[]>> getStatistics(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long userId,
            @Parameter(description = "Data de início para cálculo das estatísticas", example = "2025-11-01")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {

        List<Object[]> statistics = activityService.getStatistics(userId, startDate);
        return ResponseEntity.ok(statistics);
    }

    @Operation(summary = "Listar atividades por tipo", description = "Retorna atividades de saúde de um usuário filtradas por tipo")
    @ApiResponse(responseCode = "200", description = "Lista de atividades filtradas retornada com sucesso")
    @GetMapping("/user/{userId}/type/{type}")
    public ResponseEntity<List<HealthActivityResponseDTO>> getActivitiesByType(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long userId,
            @Parameter(description = "Tipo de atividade", examples = {
                    @io.swagger.v3.oas.annotations.media.ExampleObject(value = "EXERCISE", description = "Exercício físico"),
                    @io.swagger.v3.oas.annotations.media.ExampleObject(value = "WATER", description = "Consumo de água"),
                    @io.swagger.v3.oas.annotations.media.ExampleObject(value = "SLEEP", description = "Horas de sono"),
                    @io.swagger.v3.oas.annotations.media.ExampleObject(value = "MEDITATION", description = "Meditação"),
                    @io.swagger.v3.oas.annotations.media.ExampleObject(value = "WALKING", description = "Caminhada")
            })
            @PathVariable String type) {

        List<HealthActivityResponseDTO> activities = activityService.findByUserIdAndType(userId, type);
        return ResponseEntity.ok(activities);
    }
}
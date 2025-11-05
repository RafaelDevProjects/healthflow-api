package com.healthflow.healthflow_api.service;


import com.healthflow.healthflow_api.DTO.HealthActivityDTO;
import com.healthflow.healthflow_api.DTO.HealthActivityResponseDTO;
import com.healthflow.healthflow_api.model.HealthActivity;
import com.healthflow.healthflow_api.model.User;
import com.healthflow.healthflow_api.repository.HealthActivityRepository;
import com.healthflow.healthflow_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class HealthActivityService {

    @Autowired
    private HealthActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    public List<HealthActivityResponseDTO> findAllByUserId(Long userId) {
        List<HealthActivity> activities = activityRepository.findByUserId(userId);
        return activities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<HealthActivity> findById(Long id) {
        return activityRepository.findById(id);
    }

    public HealthActivityResponseDTO save(HealthActivityDTO activityDTO) {
        Optional<User> user = userRepository.findById(activityDTO.getUserId());
        if (user.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        HealthActivity activity = new HealthActivity();
        activity.setUser(user.get());
        activity.setActivityType(activityDTO.getActivityType());
        activity.setDate(activityDTO.getDate());
        activity.setActivityValue(activityDTO.getActivityValue());

        HealthActivity savedActivity = activityRepository.save(activity);
        return convertToDTO(savedActivity);
    }

    public void deleteById(Long id) {
        activityRepository.deleteById(id);
    }

    public List<HealthActivityResponseDTO> findByUserIdAndType(Long userId, String type) {
        List<HealthActivity> activities = activityRepository.findByUserIdAndActivityType(userId, type);
        return activities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<Object[]> getStatistics(Long userId, LocalDate startDate) {
        return activityRepository.findStatisticsByUser(userId, startDate);
    }

    private HealthActivityResponseDTO convertToDTO(HealthActivity activity) {
        return new HealthActivityResponseDTO(
                activity.getId(),
                activity.getUser().getId(),
                activity.getUser().getName(),
                activity.getActivityType(),
                activity.getDate(),
                activity.getActivityValue(),
                activity.getCreatedAt()
        );
    }

    // Método para converter entidade para DTO (útil no controller)
    public HealthActivityResponseDTO convertEntityToDTO(HealthActivity activity) {
        return convertToDTO(activity);
    }
}
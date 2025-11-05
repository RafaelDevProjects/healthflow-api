package com.healthflow.healthflow_api.repository;


import com.healthflow.healthflow_api.model.HealthActivity;
import com.healthflow.healthflow_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HealthActivityRepository extends JpaRepository<HealthActivity, Long> {

    List<HealthActivity> findByUserId(Long userId);

    List<HealthActivity> findByUserIdAndActivityType(Long userId, String activityType);

    List<HealthActivity> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT ha FROM HealthActivity ha WHERE ha.user.id = :userId AND ha.date >= :startDate")
    List<HealthActivity> findRecentActivitiesByUser(@Param("userId") Long userId,
                                                    @Param("startDate") LocalDate startDate);

    @Query("SELECT ha.activityType, SUM(ha.activityValue), AVG(ha.activityValue) " + // Atualizado
            "FROM HealthActivity ha " +
            "WHERE ha.user.id = :userId AND ha.date >= :startDate " +
            "GROUP BY ha.activityType")
    List<Object[]> findStatisticsByUser(@Param("userId") Long userId,
                                        @Param("startDate") LocalDate startDate);
}
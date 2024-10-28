package com.example.HabiTrack.repository;

import com.example.HabiTrack.model.HabitCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HabitCompletionRepository extends JpaRepository<HabitCompletion, Long> {

    List<HabitCompletion> findByHabitId(Long habitId);
}

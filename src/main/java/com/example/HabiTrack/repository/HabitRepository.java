package com.example.HabiTrack.repository;

import com.example.HabiTrack.model.Habit;
import org.springframework.data.repository.CrudRepository;

public interface HabitRepository extends CrudRepository<Habit, Long> {
}

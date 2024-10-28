package com.example.HabiTrack.service;

import com.example.HabiTrack.model.Habit;
import com.example.HabiTrack.model.HabitCompletion;
import com.example.HabiTrack.repository.HabitRepository;
import com.example.HabiTrack.repository.HabitCompletionRepository;
import com.example.HabiTrack.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HabitService {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private HabitCompletionRepository habitCompletionRepository;

    public List<Habit> getAllHabits() {
        List<Habit> habits = new ArrayList<>();
        habitRepository.findAll().forEach(habits::add);  // Convert Iterable to List
        return habits;
    }

    public Habit getHabitById(Long id) {
        return habitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id " + id));
    }

    public Habit createHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public Habit updateHabit(Long id, Habit habitDetails) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id " + id));

        habit.setName(habitDetails.getName());
        habit.setDescription(habitDetails.getDescription());
        habit.setTarget(habitDetails.getTarget());

        return habitRepository.save(habit);
    }

    public void deleteHabit(Long id) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id " + id));
        habitRepository.delete(habit);
    }

    public HabitCompletion completeHabit(Long habitId) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id " + habitId));

        HabitCompletion completion = new HabitCompletion(habit, LocalDate.now());
        return habitCompletionRepository.save(completion);
    }

    public List<HabitCompletion> getHabitCompletions(Long habitId) {
        return habitCompletionRepository.findByHabitId(habitId);
    }
}

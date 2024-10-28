package com.example.HabiTrack.controller;

import com.example.HabiTrack.model.Habit;
import com.example.HabiTrack.model.HabitCompletion;
import com.example.HabiTrack.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    @Autowired
    private HabitService habitService;

    // Get all habits
    @GetMapping
    public List<Habit> getAllHabits() {
        return habitService.getAllHabits();
    }

    // Get a habit by ID
    @GetMapping("/{id}")
    public Habit getHabitById(@PathVariable Long id) {
        return habitService.getHabitById(id);
    }

    // Complete a habit
    @PostMapping("/{id}/complete")
    public HabitCompletion completeHabit(@PathVariable Long id) {
        return habitService.completeHabit(id);
    }

    // View completions for a habit
    @GetMapping("/{id}/completions")
    public List<HabitCompletion> getHabitCompletions(@PathVariable Long id) {
        return habitService.getHabitCompletions(id);
    }

    // Create a new habit
    @PostMapping
    public Habit createHabit(@RequestBody Habit habit) {
        return habitService.createHabit(habit);
    }

    // Update an existing habit
    @PutMapping("/{id}")
    public Habit updateHabit(@PathVariable Long id, @RequestBody Habit habitDetails) {
        return habitService.updateHabit(id, habitDetails);
    }

    // Delete a habit by ID
    @DeleteMapping("/{id}")
    public void deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id);
    }
}

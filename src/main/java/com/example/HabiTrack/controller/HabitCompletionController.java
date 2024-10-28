package com.example.HabiTrack.controller;

import com.example.HabiTrack.model.HabitCompletion;
import com.example.HabiTrack.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitCompletionController {

    private final HabitService habitService;

    @Autowired
    public HabitCompletionController(HabitService habitService) {
        this.habitService = habitService;
    }

    /**
     * Completes a habit by creating a new HabitCompletion entry.
     *
     * @param habitId the ID of the habit to complete
     * @return the completed HabitCompletion object
     */
    @PostMapping("/{habitId}/complete")
    public HabitCompletion completeHabit(@PathVariable Long habitId) {
        return habitService.completeHabit(habitId);
    }

    /**
     * Retrieves all completions for a specific habit.
     *
     * @param habitId the ID of the habit to retrieve completions for
     * @return a list of HabitCompletion objects for the specified habit
     */
    @GetMapping("/{habitId}/completions")
    public List<HabitCompletion> getHabitCompletions(@PathVariable Long habitId) {
        return habitService.getHabitCompletions(habitId);
    }
}

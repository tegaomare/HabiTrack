package com.example.HabiTrack.controller;

import com.example.HabiTrack.model.Habit;
import com.example.HabiTrack.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final HabitService habitService;

    @Autowired
    public HomeController(HabitService habitService) {
        this.habitService = habitService;
    }

    /**
     * Renders the home page with a list of habits.
     */
    @GetMapping("/")
    public String index(Model model) {
        List<Habit> habits = habitService.getAllHabits();
        model.addAttribute("habits", habits);
        return "index";
    }

    /**
     * Renders the features page.
     */
    @GetMapping("/features")
    public String features() {
        return "features";
    }

    /**
     * Renders the dashboard page with a list of user's habits.
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Habit> habits = habitService.getAllHabits();
        model.addAttribute("habits", habits);
        return "dashboard";
    }
}


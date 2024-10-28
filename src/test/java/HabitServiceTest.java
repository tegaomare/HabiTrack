package com.example.HabiTrack.service;

import com.example.HabiTrack.model.Habit;
import com.example.HabiTrack.repository.HabitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class HabitServiceTest {

    @Mock
    private HabitRepository habitRepository;

    @InjectMocks
    private HabitService habitService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize mocks
    }

    @Test
    public void testCompleteHabit() {
        // Arrange
        Long habitId = 1L;
        Habit habit = new Habit("Exercise", "Daily running", 30);
        when(habitRepository.findById(habitId)).thenReturn(Optional.of(habit));

        // Act
        Habit result = habitService.getHabitById(habitId);

        // Assert
        assertEquals(habit, result);
    }
}

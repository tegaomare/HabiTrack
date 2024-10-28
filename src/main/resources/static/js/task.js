document.addEventListener("DOMContentLoaded", () => {
  const habitId = getHabitIdFromMeta();
  if (habitId) {
    attachEventListeners(habitId);
  } else {
    console.error("Habit ID not found");
  }
});

// Function to get the habit ID from the meta tag
function getHabitIdFromMeta() {
  const metaTag = document.querySelector('meta[name="habit-id"]');
  return metaTag ? metaTag.getAttribute("content") : null;
}

// Attach event listeners to buttons
function attachEventListeners(habitId) {
  const completeHabitButton = document.getElementById("complete-habit");
  const viewCompletionsButton = document.getElementById("view-completions");

  if (completeHabitButton) {
    completeHabitButton.addEventListener("click", () => completeHabit(habitId));
  }

  if (viewCompletionsButton) {
    viewCompletionsButton.addEventListener("click", () =>
      viewCompletions(habitId)
    );
  }
}

// Function to mark the habit as completed
async function completeHabit(habitId) {
  try {
    const response = await fetch(`/api/habits/${habitId}/complete`, {
      method: "POST",
    });

    if (!response.ok) {
      throw new Error("Failed to complete habit.");
    }

    alert("Habit completed successfully!");
  } catch (error) {
    alert(error.message);
    console.error("Error:", error);
  }
}

// Function to fetch and display habit completion records
async function viewCompletions(habitId) {
  try {
    const response = await fetch(`/api/habits/${habitId}/completions`);

    if (!response.ok) {
      throw new Error("Failed to load completions.");
    }

    const completions = await response.json();
    displayCompletions(completions);
    document.getElementById("habit-completions").classList.remove("hidden");
  } catch (error) {
    alert(error.message);
    console.error("Error:", error);
  }
}

// Function to display habit completion records in a list
function displayCompletions(completions) {
  const completionList = document.getElementById("completion-list");

  completionList.innerHTML = completions
    .map((completion) => `<li>Completed on: ${completion.completionDate}</li>`)
    .join("");
}

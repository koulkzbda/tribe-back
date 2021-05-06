package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.habitTracking.Habit;

public interface HabitRepo extends JpaRepository<Habit, String> {
	
}
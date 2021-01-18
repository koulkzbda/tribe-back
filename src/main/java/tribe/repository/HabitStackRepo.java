package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.HabitStack;

public interface HabitStackRepo extends JpaRepository<HabitStack, String> {
	
}
package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import tribe.domain.habitTracking.Weekday;

public interface WeekdayRepo extends JpaRepository<Weekday, String> {
	
	@Modifying
    @Query("DELETE Weekday w WHERE w.habitStack.id = ?1")
    void deleteByHabitStackId(String habitStackId);
	
}
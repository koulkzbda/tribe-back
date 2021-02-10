package tribe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tribe.domain.Metric;

public interface MetricRepo extends JpaRepository<Metric, String> {
	
}
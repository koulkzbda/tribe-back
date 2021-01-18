package tribe.repository;

import tribe.domain.System;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemRepo extends JpaRepository<System, String> {
	
}
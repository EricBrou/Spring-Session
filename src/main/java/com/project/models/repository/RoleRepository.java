package com.project.models.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.models.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	@Query("SELECT u FROM Role u WHERE u.role =:role")
	Optional<Role> findByRole(@Param("role") String role);

}

package com.project.models.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.models.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT u FROM User u "
		+  "LEFT JOIN FETCH u.roles "
		+  "WHERE u.email =:email")
	Optional<User> findByEmail(@Param("email") String email);

}

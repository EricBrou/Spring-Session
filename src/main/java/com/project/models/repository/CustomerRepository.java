package com.project.models.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.models.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	@Query("SELECT u FROM Customer u WHERE u.name =:key")
	List<Customer> findByName(@Param("key") String key);

	@Query("SELECT u FROM customer u "
		+  "LEFT JOIN FETCH u.roles "
		+  "WHERE u.email =:email")
	Optional<Customer> findByEmail(@Param("email") String email);

}

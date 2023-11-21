package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

	@Query("SELECT c.name FROM Category c WHERE c.name LIKE CONCAT('%',:name,'%')")
	List<String> findWithName(@Param("name") String name);

	boolean existsByCode(String code);
}

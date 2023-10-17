package com.example.demo.repository;


import com.example.demo.enums.RoleEnum;
import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {

  Optional<Role> findByCode(String code);

  Set<Role> findAllByCodeIn(Set<String> roles);
}

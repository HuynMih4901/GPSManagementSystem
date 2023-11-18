package com.example.demo.repository;

import com.example.demo.model.Sim;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SimRepository extends JpaRepository<Sim, Integer> {

  @Query(value = "select max(s.id) from Sim s")
  Integer findMaxId();

  Optional<Sim> findByCode(String simCode);

  @Query(value = """
select count(*) from Sim s
""")
  Integer getAllSims();
}

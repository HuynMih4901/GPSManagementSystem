package com.example.demo.repository;

import com.example.demo.model.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  boolean existsByEmail(String email);

  boolean existsByPhone(String phone);

  @Query(value = "select max(c.customerIndex) from Customer c")
  Integer findMaxIndex();

  @Query(value = """
select count(*) from Customer c
""")
  Integer getAllCustomer();

  Optional<Customer> getCustomerByCode(String code);

  boolean existsByCode(String customerCode);

  void deleteByCode(String customerCode);
}

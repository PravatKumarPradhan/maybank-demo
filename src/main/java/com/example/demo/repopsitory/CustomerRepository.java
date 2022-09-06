package com.example.demo.repopsitory;

import com.example.demo.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pravat
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
}

package com.skillbridge.repository;

import com.skillbridge.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findByFreelancerId(Long freelancerId);
    List<Service> findByCategory(String category);
    List<Service> findByPriceLessThanEqual(double price);
}
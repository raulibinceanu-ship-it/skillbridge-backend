package com.skillbridge.service;

import com.skillbridge.model.Service;
import com.skillbridge.model.User;
import com.skillbridge.repository.ServiceRepository;
import com.skillbridge.repository.UserRepository;
import com.skillbridge.security.JwtUtil;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public ServiceService(ServiceRepository serviceRepository,
                          UserRepository userRepository,
                          JwtUtil jwtUtil) {

        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public Service createService(Service service, String token) {

        String email = jwtUtil.extractEmail(token);

        User freelancer = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        service.setFreelancer(freelancer);

        return serviceRepository.save(service);
    }

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Service getServiceById(Long id) {
        return serviceRepository.findById(id).orElseThrow();
    }

    public List<Service> getServicesByFreelancer(Long freelancerId) {
        return serviceRepository.findByFreelancerId(freelancerId);
    }

    public Service updateService(Long id, Service updatedService) {

        Service service = serviceRepository.findById(id).orElseThrow();

        service.setTitle(updatedService.getTitle());
        service.setDescription(updatedService.getDescription());
        service.setPrice(updatedService.getPrice());
        service.setCategory(updatedService.getCategory());

        return serviceRepository.save(service);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
    public List<Service> getMyServices(String token) {

        String email = jwtUtil.extractEmail(token);

        User freelancer = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return serviceRepository.findByFreelancerId(freelancer.getId());
    }
    public List<Service> getServicesByCategory(String category) {
        return serviceRepository.findByCategory(category);

    }
    public List<Service> getServicesByMaxPrice(double price) {
        return serviceRepository.findByPriceLessThanEqual(price);
    }
}
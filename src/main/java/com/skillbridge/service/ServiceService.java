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

        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        service.setFreelancer(user);

        return serviceRepository.save(service);
    }

    public List<Service> getAllServices() {
        return serviceRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Service> getMyServices(String token) {

        System.out.println("TOKEN IN SERVICE: " + token);

        String email = jwtUtil.extractEmail(token);

        System.out.println("EMAIL ESTRATTA: " + email);

        User freelancer = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return serviceRepository.findByFreelancerId(freelancer.getId());
    }

    public void deleteService(Long id, String token) {

        String email = jwtUtil.extractEmail(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        if (!service.getFreelancer().getId().equals(user.getId())) {
            throw new RuntimeException("Not authorized");
        }

        serviceRepository.deleteById(id);
    }

    public List<Service> getServicesByCategory(String category) {
        return serviceRepository.findByCategory(category);
    }

    public List<Service> getServicesByMaxPrice(double price) {
        return serviceRepository.findByPriceLessThanEqual(price);
    }

    public List<Service> filterServices(String category, Double maxPrice) {

        List<Service> result;

        if (category != null && !category.isEmpty() && maxPrice != null) {
            result = serviceRepository
                    .findByCategoryContainingIgnoreCaseAndPriceLessThanEqual(category, maxPrice);

        } else if (category != null && !category.isEmpty()) {
            result = serviceRepository.findByCategoryContainingIgnoreCase(category);

        } else if (maxPrice != null) {
            result = serviceRepository.findByPriceLessThanEqual(maxPrice);

        } else {
            result = serviceRepository.findAllByOrderByCreatedAtDesc();
        }

        return result.stream()
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .toList();
    }
    public Service getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }
    public Service updateService(Long id, Service updatedService, String token) {

        String email = jwtUtil.extractEmail(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        if (!service.getFreelancer().getId().equals(user.getId())) {
            throw new RuntimeException("Not authorized");
        }

        service.setTitle(updatedService.getTitle());
        service.setDescription(updatedService.getDescription());
        service.setPrice(updatedService.getPrice());
        service.setCategory(updatedService.getCategory());
        service.setImageUrl(updatedService.getImageUrl());

        return serviceRepository.save(service);
    }
}
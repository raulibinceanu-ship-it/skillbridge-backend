package com.skillbridge.service;

import com.skillbridge.model.Service;
import com.skillbridge.repository.ServiceRepository;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Service createService(Service service) {
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
}
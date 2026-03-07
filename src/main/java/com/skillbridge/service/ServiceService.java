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
}
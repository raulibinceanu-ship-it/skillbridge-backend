package com.skillbridge.controller;

import com.skillbridge.model.Service;
import com.skillbridge.service.ServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    public Service createService(
            @RequestBody Service service,
            @RequestHeader("Authorization") String authHeader
    ) {

        String token = authHeader.replace("Bearer ", "");

        return serviceService.createService(service, token);
    }

    @GetMapping
    public List<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    @PutMapping("/{id}")
    public Service updateService(@PathVariable Long id, @RequestBody Service service) {
        return serviceService.updateService(id, service);
    }
    @GetMapping("/freelancer/{id}")
    public List<Service> getServicesByFreelancer(@PathVariable Long id) {
        return serviceService.getServicesByFreelancer(id);
    }
    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
    }

}
package com.skillbridge.controller;

import com.skillbridge.model.Service;
import com.skillbridge.service.ServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "http://localhost:3000")
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
    @GetMapping("/my-services")
    public List<Service> getMyServices(
            @RequestHeader("Authorization") String authHeader
    ) {

        String token = authHeader.replace("Bearer ", "");

        return serviceService.getMyServices(token);
    }
    @GetMapping("/category/{category}")
    public List<Service> getServicesByCategory(@PathVariable String category) {
        return serviceService.getServicesByCategory(category);
    }
    @GetMapping("/price/{price}")
    public List<Service> getServicesByMaxPrice(@PathVariable double price) {
        return serviceService.getServicesByMaxPrice(price);
    }
    @GetMapping("/{id}")
    public Service getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id);
    }
}
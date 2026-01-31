package com.example.demo.service;

import com.example.demo.model.Route;
import com.example.demo.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {
    
    @Autowired
    private RouteRepository routeRepository;
    
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }
    
    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }
    
    public Optional<Route> getRouteByNumber(String routeNumber) {
        return routeRepository.findByRouteNumber(routeNumber);
    }
    
    public List<Route> getRoutesByStatus(String status) {
        return routeRepository.findByStatus(status);
    }
    
    public List<Route> getRoutesBySourceAndDestination(String source, String destination) {
        return routeRepository.findBySourceAndDestination(source, destination);
    }
    
    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }
    
    public Route updateRoute(Long id, Route routeDetails) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + id));
        
        route.setRouteNumber(routeDetails.getRouteNumber());
        route.setSource(routeDetails.getSource());
        route.setDestination(routeDetails.getDestination());
        route.setDistance(routeDetails.getDistance());
        route.setEstimatedDuration(routeDetails.getEstimatedDuration());
        route.setStops(routeDetails.getStops());
        route.setStatus(routeDetails.getStatus());
        
        return routeRepository.save(route);
    }
    
    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }
}

package com.example.demo.controller;

import com.example.demo.model.Route;
import com.example.demo.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = {"http://localhost:3000", "https://bus-travel-log.vercel.app"})
public class RouteController {
    
    @Autowired
    private RouteService routeService;
    
    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable Long id) {
        return routeService.getRouteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/number/{routeNumber}")
    public ResponseEntity<Route> getRouteByNumber(@PathVariable String routeNumber) {
        return routeService.getRouteByNumber(routeNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Route>> getRoutesByStatus(@PathVariable String status) {
        return ResponseEntity.ok(routeService.getRoutesByStatus(status));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Route>> getRoutesBySourceAndDestination(
            @RequestParam String source, 
            @RequestParam String destination) {
        return ResponseEntity.ok(routeService.getRoutesBySourceAndDestination(source, destination));
    }
    
    @PostMapping
    public ResponseEntity<?> createRoute(@Valid @RequestBody Route route, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> 
                errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(routeService.createRoute(route));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoute(@PathVariable Long id, @Valid @RequestBody Route route, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> 
                errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return ResponseEntity.ok(routeService.updateRoute(id, route));
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}

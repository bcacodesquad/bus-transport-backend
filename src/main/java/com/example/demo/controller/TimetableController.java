package com.example.demo.controller;

import com.example.demo.model.Timetable;
import com.example.demo.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/timetables")
public class TimetableController {
    
    @Autowired
    private TimetableService timetableService;
    
    @GetMapping
    public ResponseEntity<List<Timetable>> getAllTimetables() {
        return ResponseEntity.ok(timetableService.getAllTimetables());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Timetable> getTimetableById(@PathVariable Long id) {
        return timetableService.getTimetableById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Timetable>> getTimetablesByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(timetableService.getTimetablesByDate(date));
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Timetable>> getTimetablesByStatus(@PathVariable String status) {
        return ResponseEntity.ok(timetableService.getTimetablesByStatus(status));
    }
    
    @GetMapping("/range")
    public ResponseEntity<List<Timetable>> getTimetablesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(timetableService.getTimetablesByDateRange(startDate, endDate));
    }
    
    @PostMapping
    public ResponseEntity<?> createTimetable(@Valid @RequestBody Timetable timetable, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> 
                errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(timetableService.createTimetable(timetable));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTimetable(@PathVariable Long id, @Valid @RequestBody Timetable timetable, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> 
                errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return ResponseEntity.ok(timetableService.updateTimetable(id, timetable));
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimetable(@PathVariable Long id) {
        timetableService.deleteTimetable(id);
        return ResponseEntity.noContent().build();
    }
}

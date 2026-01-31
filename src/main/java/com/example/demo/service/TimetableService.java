package com.example.demo.service;

import com.example.demo.model.Timetable;
import com.example.demo.model.Schedule;
import com.example.demo.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TimetableService {
    
    @Autowired
    private TimetableRepository timetableRepository;
    
    public List<Timetable> getAllTimetables() {
        return timetableRepository.findAll();
    }
    
    public Optional<Timetable> getTimetableById(Long id) {
        return timetableRepository.findById(id);
    }
    
    public List<Timetable> getTimetablesBySchedule(Schedule schedule) {
        return timetableRepository.findBySchedule(schedule);
    }
    
    public List<Timetable> getTimetablesByDate(LocalDate date) {
        return timetableRepository.findByDate(date);
    }
    
    public List<Timetable> getTimetablesByStatus(String status) {
        return timetableRepository.findByStatus(status);
    }
    
    public List<Timetable> getTimetablesByDateRange(LocalDate startDate, LocalDate endDate) {
        return timetableRepository.findByDateBetween(startDate, endDate);
    }
    
    public Timetable createTimetable(Timetable timetable) {
        return timetableRepository.save(timetable);
    }
    
    public Timetable updateTimetable(Long id, Timetable timetableDetails) {
        Timetable timetable = timetableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timetable not found with id: " + id));
        
        timetable.setSchedule(timetableDetails.getSchedule());
        timetable.setDate(timetableDetails.getDate());
        timetable.setScheduledDeparture(timetableDetails.getScheduledDeparture());
        timetable.setScheduledArrival(timetableDetails.getScheduledArrival());
        timetable.setActualDeparture(timetableDetails.getActualDeparture());
        timetable.setActualArrival(timetableDetails.getActualArrival());
        timetable.setStatus(timetableDetails.getStatus());
        timetable.setRemarks(timetableDetails.getRemarks());
        
        return timetableRepository.save(timetable);
    }
    
    public void deleteTimetable(Long id) {
        timetableRepository.deleteById(id);
    }
}

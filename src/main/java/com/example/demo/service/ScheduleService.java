package com.example.demo.service;

import com.example.demo.model.Schedule;
import com.example.demo.model.Bus;
import com.example.demo.model.Route;
import com.example.demo.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
    
    public Optional<Schedule> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }
    
    public List<Schedule> getSchedulesByBus(Bus bus) {
        return scheduleRepository.findByBus(bus);
    }
    
    public List<Schedule> getSchedulesByRoute(Route route) {
        return scheduleRepository.findByRoute(route);
    }
    
    public List<Schedule> getSchedulesByStatus(String status) {
        return scheduleRepository.findByStatus(status);
    }
    
    public List<Schedule> getSchedulesByFrequency(String frequency) {
        return scheduleRepository.findByFrequency(frequency);
    }
    
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }
    
    public Schedule updateSchedule(Long id, Schedule scheduleDetails) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
        
        schedule.setBus(scheduleDetails.getBus());
        schedule.setRoute(scheduleDetails.getRoute());
        schedule.setDepartureTime(scheduleDetails.getDepartureTime());
        schedule.setArrivalTime(scheduleDetails.getArrivalTime());
        schedule.setFrequency(scheduleDetails.getFrequency());
        schedule.setStatus(scheduleDetails.getStatus());
        
        return scheduleRepository.save(schedule);
    }
    
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}

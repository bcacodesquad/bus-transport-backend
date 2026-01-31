package com.example.demo.service;

import com.example.demo.model.Bus;
import com.example.demo.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {
    
    @Autowired
    private BusRepository busRepository;
    
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }
    
    public Optional<Bus> getBusById(Long id) {
        return busRepository.findById(id);
    }
    
    public Optional<Bus> getBusByNumber(String busNumber) {
        return busRepository.findByBusNumber(busNumber);
    }
    
    public List<Bus> getBusesByStatus(String status) {
        return busRepository.findByStatus(status);
    }
    
    public List<Bus> getBusesByType(String busType) {
        return busRepository.findByBusType(busType);
    }
    
    public Bus createBus(Bus bus) {
        return busRepository.save(bus);
    }
    
    public Bus updateBus(Long id, Bus busDetails) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found with id: " + id));
        
        bus.setBusNumber(busDetails.getBusNumber());
        bus.setBusType(busDetails.getBusType());
        bus.setTotalSeats(busDetails.getTotalSeats());
        bus.setRegistrationNumber(busDetails.getRegistrationNumber());
        bus.setStatus(busDetails.getStatus());
        
        return busRepository.save(bus);
    }
    
    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }
}

package com.example.demo.repository;

import com.example.demo.model.Schedule;
import com.example.demo.model.Bus;
import com.example.demo.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByBus(Bus bus);
    List<Schedule> findByRoute(Route route);
    List<Schedule> findByStatus(String status);
    List<Schedule> findByFrequency(String frequency);
}

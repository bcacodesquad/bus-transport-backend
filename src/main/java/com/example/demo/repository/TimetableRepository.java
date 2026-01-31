package com.example.demo.repository;

import com.example.demo.model.Timetable;
import com.example.demo.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    List<Timetable> findBySchedule(Schedule schedule);
    List<Timetable> findByDate(LocalDate date);
    List<Timetable> findByStatus(String status);
    List<Timetable> findByDateBetween(LocalDate startDate, LocalDate endDate);
}

package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "timetables")
public class Timetable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Schedule is required")
    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
    
    @NotNull(message = "Date is required")
    @Column(nullable = false)
    private LocalDate date;
    
    @NotNull(message = "Scheduled departure time is required")
    @Column(nullable = false)
    private LocalTime scheduledDeparture;
    
    @NotNull(message = "Scheduled arrival time is required")
    @Column(nullable = false)
    private LocalTime scheduledArrival;
    
    private LocalTime actualDeparture;
    
    private LocalTime actualArrival;
    
    private String status; // On Time, Delayed, Cancelled
    
    private String remarks;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public Timetable() {}
    
    public Timetable(Schedule schedule, LocalDate date, LocalTime scheduledDeparture, LocalTime scheduledArrival, String status) {
        this.schedule = schedule;
        this.date = date;
        this.scheduledDeparture = scheduledDeparture;
        this.scheduledArrival = scheduledArrival;
        this.status = status;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Schedule getSchedule() {
        return schedule;
    }
    
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public LocalTime getScheduledDeparture() {
        return scheduledDeparture;
    }
    
    public void setScheduledDeparture(LocalTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }
    
    public LocalTime getScheduledArrival() {
        return scheduledArrival;
    }
    
    public void setScheduledArrival(LocalTime scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }
    
    public LocalTime getActualDeparture() {
        return actualDeparture;
    }
    
    public void setActualDeparture(LocalTime actualDeparture) {
        this.actualDeparture = actualDeparture;
    }
    
    public LocalTime getActualArrival() {
        return actualArrival;
    }
    
    public void setActualArrival(LocalTime actualArrival) {
        this.actualArrival = actualArrival;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

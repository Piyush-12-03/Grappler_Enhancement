package com.example.grappler.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long projectId;

    @OneToMany(mappedBy = "projects", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Worklogs> worklogs;

    private String name;
    private String description;
    private LocalDateTime start_date; //datetime format
    private LocalDateTime end_date; //datetime format


    @OneToMany(mappedBy = "projects")
    private List<Tickets> tickets;

    public List<Worklogs> getWorklogs() {
        return worklogs;
    }

    public void setWorklogs(List<Worklogs> worklogs) {
        this.worklogs = worklogs;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }
}
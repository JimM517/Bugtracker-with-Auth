package com.techelevator.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class BugList {

    private int id;
    private String name;
    private String description;
    private int createdBy;
    private LocalDateTime createdAt;

    public BugList(int id, String name, String description, int createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }


    public BugList() {};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return "BugList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugList bugList = (BugList) o;
        return id == bugList.id && createdBy == bugList.createdBy && Objects.equals(name, bugList.name) && Objects.equals(description, bugList.description) && Objects.equals(createdAt, bugList.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, createdBy, createdAt);
    }
}

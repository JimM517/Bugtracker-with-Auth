package com.techelevator.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Tickets {

    private int id;
    private String title;
    private String description;
    private String status;
    private int createdBy;
    private LocalDateTime createdAt;
    private int bugListId;

    private List<Comments> comments;


    public Tickets(int id, String title, String description, String status, int createdBy, LocalDateTime createdAt, int bugListId, List<Comments> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.bugListId = bugListId;
        this.comments = comments;
    }

    public Tickets() {};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getBugListId() {
        return bugListId;
    }

    public void setBugListId(int bugListId) {
        this.bugListId = bugListId;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", bugListId=" + bugListId +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tickets tickets = (Tickets) o;
        return id == tickets.id && createdBy == tickets.createdBy && bugListId == tickets.bugListId && Objects.equals(title, tickets.title) && Objects.equals(description, tickets.description) && Objects.equals(status, tickets.status) && Objects.equals(createdAt, tickets.createdAt) && Objects.equals(comments, tickets.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, status, createdBy, createdAt, bugListId, comments);
    }
}

package com.techelevator.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comments {

    private int id;
    private String content;
    private int createdBy;
    private LocalDateTime createdAt;
    private int ticketId;

    public Comments(int id, String content, int createdBy, LocalDateTime createdAt, int ticketId) {
        this.id = id;
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.ticketId = ticketId;
    }

    public Comments() {};


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }


    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", ticketId=" + ticketId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return id == comments.id && createdBy == comments.createdBy && ticketId == comments.ticketId && Objects.equals(content, comments.content) && Objects.equals(createdAt, comments.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, createdBy, createdAt, ticketId);
    }
}

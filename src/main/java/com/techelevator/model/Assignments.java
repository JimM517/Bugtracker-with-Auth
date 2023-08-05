package com.techelevator.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Assignments {

    private int id;
    private int ticketId;
    private int userId;
    private LocalDateTime assignedAt;

    public Assignments(int id, int ticketId, int userId, LocalDateTime assignedAt) {
        this.id = id;
        this.ticketId = ticketId;
        this.userId = userId;
        this.assignedAt = assignedAt;
    }

    public Assignments() {};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    @Override
    public String toString() {
        return "Assignments{" +
                "id=" + id +
                ", ticketId=" + ticketId +
                ", userId=" + userId +
                ", assignedAt=" + assignedAt +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignments that = (Assignments) o;
        return id == that.id && ticketId == that.ticketId && userId == that.userId && Objects.equals(assignedAt, that.assignedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketId, userId, assignedAt);
    }
}

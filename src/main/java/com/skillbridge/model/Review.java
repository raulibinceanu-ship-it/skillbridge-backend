package com.skillbridge.model;

import jakarta.persistence.*;
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;
    private String comment;

    @ManyToOne
    private Service service;

    @ManyToOne
    private User user;

    public Review() {}

    public Long getId() { return id; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public Service getService() { return service; }
    public User getUser() { return user; }

    public void setRating(int rating) { this.rating = rating; }
    public void setComment(String comment) { this.comment = comment; }
    public void setService(Service service) { this.service = service; }
    public void setUser(User user) { this.user = user; }
}

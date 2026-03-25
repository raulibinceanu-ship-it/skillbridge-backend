package com.skillbridge.model;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private int rating;

    @ManyToOne
    private Service service;

    public Review() {}

    public Long getId() { return id; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public Service getService() { return service; }
    public void setService(Service service) { this.service = service; }
}
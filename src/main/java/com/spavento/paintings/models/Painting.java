package com.spavento.paintings.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "paintings")
public class Painting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "price")
    private Float price;
    @Column(name = "status")
    private String status;
    @Column(name = "category")
    private String category;
    @Column(name = "date_added", nullable = false)
    private LocalDate date_added;

    public Painting() {this.date_added = LocalDate.now();}
    public Painting(String title, String description, Float price, String status, String category) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = status;
        this.category = category;
        this.date_added = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate_added() {
        return date_added;
    }

    public void setDate_added(LocalDate date_added) {
        this.date_added = date_added;
    }
}

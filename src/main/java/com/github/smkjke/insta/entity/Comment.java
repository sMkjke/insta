package com.github.smkjke.insta.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private Long userId;
    @Column(columnDefinition = "text", nullable = false)
    private String message;
    @Column(updatable = false)
    private LocalDateTime createdDate;

    /**
     * On create. Track when was create that or another object in DB.
     * if we create a new entity and call the save method of our repository,
     * our method annotated with @PrePersist is called, then the record is inserted into the database
     */
    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

}

package com.github.smkjke.insta.entity;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

public class Comment {

    private Long id;
    private Post post;
    private String username;
    private Long userId;
    private String message;
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

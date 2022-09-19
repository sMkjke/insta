package com.github.smkjke.insta.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String caption;
    private String location;
    private Integer likes;
    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> likedUsers = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER,mappedBy = "post",orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
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

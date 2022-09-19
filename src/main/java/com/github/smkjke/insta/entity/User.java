package com.github.smkjke.insta.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.smkjke.insta.entity.enums.ERole;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, updatable = false)
    private String userName;
    @Column(unique = true, updatable = false)
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(columnDefinition = "text") // to save more than 50 symbols in bio;
    private String bio;
    @Column(length = 3000)
    private String password;

    public User() {

    }

    @ElementCollection(targetClass = ERole.class) // dependency User = Role; one to many
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> roleSet = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    // CascadeType.All - when we delete User -> all posts will be deleted too, FetchType.Lazy = if we enter into User, we dont need to get all user posts
    private List<Post> postList = new ArrayList<>();
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

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

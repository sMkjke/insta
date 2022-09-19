package com.github.smkjke.insta.entity;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Data
@Entity
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageBytes; //save the picture like a blob
    @JsonIgnore
    private Long userId; //photo for user
    @JsonIgnore
    private Long postId; //photo for user post

}

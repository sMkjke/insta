package com.github.smkjke.insta.entity;

public class ImageModel {

    private long id;
    private String name;
    private byte[] imageBytes; //save the picture like a blob
    private Long userId; //photo for user
    private Long postId; //photo for user post

}

package com.example.backend.vo;

public class UserVO {
    private String username;
    private String avatar;
    private String bio;
    private String email;

    public UserVO(String username, String avatar, String bio, String email) {
        this.username = username;
        this.avatar = avatar;
        this.bio = bio;
        this.email = email;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getBio() {
        return bio;
    }

    public String getEmail() {
        return email;
    }
}

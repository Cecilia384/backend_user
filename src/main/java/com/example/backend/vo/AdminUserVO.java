package com.example.backend.vo;

public class AdminUserVO {
    private String username;
    private String avatar;
    private String bio;
    private String email;
    private String role;

    public AdminUserVO(String username, String avatar, String bio, String email, String role) {
        this.username = username;
        this.avatar = avatar;
        this.bio = bio;
        this.email = email;
        this.role = role;
    }

    // Getters 和 Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

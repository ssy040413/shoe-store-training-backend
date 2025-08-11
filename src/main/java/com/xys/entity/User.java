package com.xys.entity;

import java.time.LocalDateTime;

/**
 * 用户实体类, 精确映射新的 'users' 表
 */
public class User {

    private Long id; // ID类型为 Long，对应数据库的 BIGINT
    private String username;
    private String password; // 注意：这个字段通常只用于接收新增/修改时的密码，不应返回给前端
    private String nickname;
    private String email;
    private String phone;
    private String avatarUrl; // 驼峰命名法对应数据库的 avatar_url
    private Integer gender; // 对应 TINYINT
    private Integer status; // 对应 TINYINT
    private LocalDateTime lastLoginAt; // 驼峰命名法对应 last_login_at
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // --- 标准的 Getters 和 Setters ---
    // 可在IDE中自动生成 (右键 -> Generate -> Getter and Setter)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
package com.wavemaker.model;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
@Proxy(lazy=false)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int userId;
    @Column(name = "USERNAME")
    private String username;
    @NaturalId
    @Column(name = "EMAIL")
    private String userEmail;
    @Column(name = "PHONE_NO")
    private String userPhoneNo;
    @Column(name = "ADDRESS")
    private String userAddress;
    @Column(name = "PASSWORD")
    private String userPassword;
    @Column(name = "ENABLED")
    private boolean enabled;
    @Column(name = "ROLE")
    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_BOOKS", joinColumns = @JoinColumn(name = "USERID"), inverseJoinColumns = @JoinColumn(name = "BOOKID"))
    private Set<Book> books = new HashSet<>();

    public User(String username, String userEmail, String userPhoneNo, String userAddress, String userPassword, boolean enabled, String role) {
        this.username = username;
        this.userEmail = userEmail;
        this.userPhoneNo = userPhoneNo;
        this.userAddress = userAddress;
        this.userPassword = userPassword;
        this.enabled = enabled;
        this.role = role;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public void setUserPhoneNo(String userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + username + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhoneNo='" + userPhoneNo + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

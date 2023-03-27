package com.wavemaker.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORDETAILS")
@Proxy(lazy=false)
public class AuthorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUTHOR_ID")
    private int authorId;
    @Column(name = "AUTHOR_NAME")
    private String authorName;
    @Column(name = "AUTHOR_EMAIL")
    private String authorEmail;
    @Column(name = "AUTHOR_PHONE_NO")
    private String authorPhoneNo;
    @Column(name = "AUTHOR_ADDRESS")
    private String authorAddress;

    public AuthorDetails(String authorName, String authorEmail, String authorPhoneNo, String authorAddress) {
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.authorPhoneNo = authorPhoneNo;
        this.authorAddress = authorAddress;
    }

    public AuthorDetails() {
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getAuthorPhoneNo() {
        return authorPhoneNo;
    }

    public void setAuthorPhoneNo(String authorPhoneNo) {
        this.authorPhoneNo = authorPhoneNo;
    }

    public String getAuthorAddress() {
        return authorAddress;
    }

    public void setAuthorAddress(String authorAddress) {
        this.authorAddress = authorAddress;
    }


    @Override
    public String toString() {
        return "AuthorDetails{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", authorEmail='" + authorEmail + '\'' +
                ", authorPhoneNo='" + authorPhoneNo + '\'' +
                ", authorAddress='" + authorAddress + '\'' +
                '}';
    }
}

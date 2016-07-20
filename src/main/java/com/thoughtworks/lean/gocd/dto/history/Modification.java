package com.thoughtworks.lean.gocd.dto.history;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Modification {

    @JsonProperty("email_address")
    private String emailAddress;
    private int id;
    @JsonProperty("modified_time")
    private Date modifiedTime;
    @JsonProperty("user_name")
    private String userName;
    private String comment;
    private String revision;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    @Override
    public String toString() {
        return "Modification{" +
                "emailAddress='" + emailAddress + '\'' +
                ", id=" + id +
                ", modifiedTime=" + modifiedTime +
                ", userName='" + userName + '\'' +
                ", comment='" + comment + '\'' +
                ", revision='" + revision + '\'' +
                '}';
    }
}

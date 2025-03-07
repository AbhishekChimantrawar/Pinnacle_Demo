package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "send_msg")
public class SendMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long mobile;
    private String message;
    private String status;

    @Column(name = "received_ts")
    private Timestamp receivedTs;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "telco_response")
    private String telcoResponse;

    @Column(name = "sent_ts")
    private Timestamp sentTs;

    // Constructors
    public SendMessage() {}

    public SendMessage(Long mobile, String message, String status, Timestamp receivedTs, Integer accountId) {
        this.mobile = mobile;
        this.message = message;
        this.status = status;
        this.receivedTs = receivedTs;
        this.accountId = accountId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getReceivedTs() {
        return receivedTs;
    }

    public void setReceivedTs(Timestamp receivedTs) {
        this.receivedTs = receivedTs;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getTelcoResponse() {
        return telcoResponse;
    }

    public void setTelcoResponse(String telcoResponse) {
        this.telcoResponse = telcoResponse;
    }

    public Timestamp getSentTs() {
        return sentTs;
    }

    public void setSentTs(Timestamp sentTs) {
        this.sentTs = sentTs;
    }
}

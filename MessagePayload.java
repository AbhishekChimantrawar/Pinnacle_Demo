package com.example.demo;

public class MessagePayload {

    private String ackId;
    private Integer accountId;
    private Long mobile;
    private String message;

    // Constructor
    public MessagePayload(String ackId, Integer accountId, Long mobile, String message) {
        this.ackId = ackId;
        this.accountId = accountId;
        this.mobile = mobile;
        this.message = message;
    }

    // Getters and Setters
    public String getAckId() {
        return ackId;
    }

    public void setAckId(String ackId) {
        this.ackId = ackId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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

    @Override
    public String toString() {
        return "MessagePayload{" +
                "ackId='" + ackId + '\'' +
                ", accountId=" + accountId +
                ", mobile=" + mobile +
                ", message='" + message + '\'' +
                '}';
    }
}

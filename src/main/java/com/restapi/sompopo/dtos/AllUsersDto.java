package com.restapi.sompopo.dtos;

import java.util.Date;

public class AllUsersDto {
    private Long id;
    private String username;
    private String email;
    private String msisdn;
    private Date createdAt;

    public AllUsersDto(Long id, String username, String email, String msisdn, Date createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.msisdn = msisdn;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMsisdn() { return msisdn; }
    public void setMsisdn(String msisdn) { this.msisdn = msisdn; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}

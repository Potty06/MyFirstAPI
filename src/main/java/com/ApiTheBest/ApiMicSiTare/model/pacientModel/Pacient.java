package com.ApiTheBest.ApiMicSiTare.model.pacientModel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor

public class Pacient {
    private Integer pacientId;

    private String pacientName;

    private String address;

    private String email;

    private String phoneNo;

    public Integer getPacientId() {
        return pacientId;
    }

    public void setPacientId(Integer pacientId) {
        this.pacientId = pacientId;
    }

    public String getPacientName() {
        return pacientName;
    }

    public void setPacientName(String pacientName) {
        this.pacientName = pacientName;
    }

    public String getAdress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}

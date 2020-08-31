package com.ApiTheBest.ApiMicSiTare.model.pacientModel.updatePacient;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.*;


/**
 * the pacient updated into DB
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "pacientId",
        "pacientName",
        "address",
        "email",
        "phoneNo"
})

public class UpdatePacient {

    /**
     * Pacient id.
     */
    @JsonProperty("pacientId")
    @JsonPropertyDescription(" Pacient id.")
    private Integer pacientId;
    /**
     * Name of the pacient.
     */
    @JsonProperty("pacientName")
    @JsonPropertyDescription(" Name of the pacient.")
    @NotNull
    @Size(min = 5, message = "Invalid name")
    @Pattern(regexp = "[a-zA-Z ]*")
    private String pacientName;
    /**
     * The pacient address.
     */
    @JsonProperty("address")
    @JsonPropertyDescription(" The pacient address.")
    private String address;
    /**
     * The pacient email.
     */
    @JsonProperty("email")
    @JsonPropertyDescription(" The pacient email.")
    @NotNull
    @Size(min = 8)
    @Email(message = "Wrong type of email!")
    private String email;
    /**
     * The phone number of the pacient.
     */
    @JsonProperty("phoneNo")
    @JsonPropertyDescription(" The phone number of the pacient. ")
    @NotNull
    @Pattern(regexp = "(\\+40|0)[0-9]{9}")
    private String phoneNo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Pacient id.
     */
    @JsonProperty("pacientId")
    public Integer getPacientId() {
        return pacientId;
    }

    /**
     * Pacient id.
     */
    @JsonProperty("pacientId")
    public void setPacientId(Integer pacientId) {
        this.pacientId = pacientId;
    }

    /**
     * Name of the pacient.
     */
    @JsonProperty("pacientName")
    public String getPacientName() {
        return pacientName;
    }

    /**
     * Name of the pacient.
     */
    @JsonProperty("pacientName")
    public void setPacientName(String pacientName) {
        this.pacientName = pacientName;
    }

    /**
     * The pacient address.
     */
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    /**
     * The pacient address.
     */
    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * The pacient email.
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    /**
     * The pacient email.
     */
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * The phone number of the pacient.
     */
    @JsonProperty("phoneNo")
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * The phone number of the pacient.
     */
    @JsonProperty("phoneNo")
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "UpdatePacient{" +
                "pacientId=" + pacientId +
                ", pacientName='" + pacientName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

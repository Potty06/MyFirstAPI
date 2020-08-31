package com.ApiTheBest.ApiMicSiTare.model.pacientModel.addPacient;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

/**
 * the customer inserted into DB
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "pacientName",
        "address",
        "email",
        "phoneNo"
})
public class AddPacient {

    /**
     * Name of the pacient.
     */
    @JsonProperty("pacientName")
    @JsonPropertyDescription(" Name of the pacient.")
    @NotNull
    @Size(min = 10, message = "Invalid name")
    private String pacientName;

    /**
     * Address of the pacient.
     */
    @JsonProperty("address")
    @JsonPropertyDescription(" Address of the pacient.")
    @NotNull
    private String address;

    /**
     * Email of the pacient.
     */
    @JsonProperty("email")
    @JsonPropertyDescription(" Email of the pacient.")
    @NotNull
    private String email;

    /**
     * The phone number of the pacient.
     */
    @JsonProperty("phoneNo")
    @JsonPropertyDescription(" The phone number of the doctor. ")
    @Size(min = 9, max = 13, message = "Phone no should be from 2 and 34 digits maxim")
    @Pattern(regexp = "(\\+40|0)[0-9]{9}")
    private String phoneNo;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
    @JsonProperty("doctorName")
    public void setPacientName(String Name) {
        this.pacientName = pacientName;
    }


    /**
     * Address of the pacient.
     */
    @JsonProperty("address")
    public String getPacientAddress() {
        return address;
    }

    /**
     * Address of the pacient.
     */
    @JsonProperty("address")
    public void setPacientAddress(String Address) {
        this.address = Address;
    }

    /**
     * Email of the pacient.
     */
    @JsonProperty("email")
    public String getPacientEmail() {
        return email;
    }

    /**
     * Email of the pacient.
     */
    @JsonProperty("email")
    public void setPacientEmail(String email) {
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
        return "AddPacient{" +
                "pacientName='" + pacientName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
package com.ApiTheBest.ApiMicSiTare.model.doctorModel.addDoctor;


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



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "doctorName",
        "phoneNo",
        "contract",
        "medicalSpeciality"
})
public class AddDoctor {

    /**
     * Name of the doctor.
     */
    @JsonProperty("doctorName")
    @JsonPropertyDescription(" Name of the customer.")
    //@NotNull
    @Size(min = 3, message = "Invalid name")
    private String doctorName;
    /**
     * The phone number of the doctor.
     */
    @JsonProperty("phoneNo")
    @JsonPropertyDescription(" The phone number of the doctor. ")
    @Size(min = 9, max = 13, message = "Phone no should be from 2 and 34 digits maxim")
    @Pattern(regexp = "(\\+40|0)[0-9]{9}")
    private String phoneNo;

    /**
     * Type of contract.
     */
    @JsonProperty("contract")
    @JsonPropertyDescription(" Type of contract")
    //@NotNull
// sa nu uiti ma mamaie ma sa scrii pattern pt full time/part time
    private String contract;
    /**
     * Medic Speciality.
     */
    @JsonProperty("medicSpeciality")
    @JsonPropertyDescription(" Medic Speciality.")
    //@NotNull
// validari de facut
    private String medicSpeciality;



    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Name of the doctor.
     */
    @JsonProperty("doctorName")
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * Name of the doctor.
     */
    @JsonProperty("doctorName")
    public void setDoctorName(String Name) {
        this.doctorName = doctorName;
    }
//git

    /**
     * The phone number of the doctor.
     */
    @JsonProperty("phoneNo")
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * The phone number of the customer.
     */
    @JsonProperty("phoneNo")
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    @JsonProperty("contract")
    public String getContract() {
        return contract;
    }
    @JsonProperty("contract")
    public void setContract(String contract) {
        this.contract = contract;
    }
    @JsonProperty("medicSpeciality")
    public String getMedicSpeciality() {
        return medicSpeciality;
    }
    @JsonProperty("medicSpeciality")
    public void setMedicSpeciality(String medicSpeciality) {
        this.medicSpeciality = medicSpeciality;
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
        return "AddDoctor{" +
                "doctorName='" + doctorName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

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

import javax.validation.Valid;

/**
 * addCustomerReq
 * <p>
 * Add a new Customer to the DB.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "pacient"
})
public class AddPacientRequest {

    /**
     * the pacient inserted into DB
     *
     */
    @JsonProperty("pacient")
    @JsonPropertyDescription(" the pacient inserted into DB")
    @Valid
    private AddPacient pacient;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * the pacient inserted into DB
     *
     */
    @JsonProperty("pacient")
    public AddPacient getPacient() {
        return pacient;
    }

    /**
     * the customer inserted into DB
     *
     */
    @JsonProperty("pacient")
    public void setPacient(AddPacient customer) {
        this.pacient = pacient;
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
        return "AddPacientRequest{" +
                "pacient=" + pacient +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
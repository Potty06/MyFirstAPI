package com.ApiTheBest.ApiMicSiTare.model.pacientModel.updatePacient;

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
 * updatePacientReq
 * <p>
 * update a pacient structure in DB.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customer"
})
public class UpdatePacientRequest {

    /**
     * the pacient updated into DB
     *
     */
    @JsonProperty("pacient")
    @JsonPropertyDescription(" the pacient updated into DB")
    @Valid
    private UpdatePacient pacient;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * the pacient updated into DB
     *
     */
    @JsonProperty("pacient")
    public UpdatePacient getPacient() {
        return pacient;
    }

    /**
     * the pacient updated into DB
     *
     */
    @JsonProperty("pacient")
    public void setPacient(UpdatePacient pacient) {
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
        return "UpdatePacientRequest{" +
                "pacient=" + pacient +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
package com.ApiTheBest.ApiMicSiTare.model.pacientModel.addPacient;


import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * addPacientRsp
 * <p>
 * response after inserting a new customer structure into DB.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "pacientId",
        "responseDescription"
})
public class AddPacientResponse {

    /**
     * The id of the new created pacient.
     *
     */
    @JsonProperty("pacientId")
    @JsonPropertyDescription(" The id of the new created pacient.")
    private Integer pacientId;
    /**
     * Description of the status.
     *
     */
    @JsonProperty("responseDescription")
    @JsonPropertyDescription(" Description of the status.")
    private String responseDescription;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * The id of the new created pacient.
     *
     */
    @JsonProperty("pacientId")
    public Integer getPacientId() {
        return pacientId;
    }

    /**
     * The id of the new created customer.
     *
     */
    @JsonProperty("customerId")
    public void setPacientId(Integer pacientId) {
        this.pacientId = pacientId;
    }

    /**
     * Description of the status.
     *
     */
    @JsonProperty("responseDescription")
    public String getResponseDescription() {
        return responseDescription;
    }

    /**
     * Description of the status.
     *
     */
    @JsonProperty("responseDescription")
    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
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
        return "AddCustomerResponse{" +
                "pacientId=" + pacientId +
                ", responseDescription='" + responseDescription + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

}

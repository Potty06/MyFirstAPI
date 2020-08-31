package com.ApiTheBest.ApiMicSiTare.model.doctorModel.updateDoctor;

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


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "doctor"
})

public class UpdateDoctorRequest {
}

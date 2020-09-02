package com.ApiTheBest.ApiMicSiTare.model.appointmentModel.getAppointment;

import com.ApiTheBest.ApiMicSiTare.model.doctorModel.Doctor;
import com.ApiTheBest.ApiMicSiTare.model.patientModel.Patient;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "appointmentId",
        "pacient",
        "doctor",
        "appointmentDate",
        "appointmentTime"
})

public class GetAppointment {

    @JsonProperty("appointmentId")
    @JsonPropertyDescription(" appointmentId ")
    private Integer appointmentId;

    @JsonProperty("pacient")
    @JsonPropertyDescription(" The pacient ")
    @NotNull

    private Patient pacient;

    @JsonProperty("doctor")
    @JsonPropertyDescription(" The doctor")
    private Doctor doctor;

    @JsonProperty("appointmentDate")
    @JsonPropertyDescription(" The appointmentDate.")
    private LocalDate appointmentDate;

    @JsonProperty("appointmentTime")
    @JsonPropertyDescription(" The appointmentDate.")
    private LocalTime appointmentTime;

    @Override
    public String toString() {
        return "GetAppointment{" +
                "appointmentId=" + appointmentId +
                ", pacient=" + pacient +
                ", doctor=" + doctor +
                ", appointmentDate=" + appointmentDate +
                ", appointmentTime=" + appointmentTime +
                '}';
    }
}

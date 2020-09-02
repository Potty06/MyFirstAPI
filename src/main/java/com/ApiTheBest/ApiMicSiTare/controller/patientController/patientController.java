package com.ApiTheBest.ApiMicSiTare.controller.patientController;

import com.ApiTheBest.ApiMicSiTare.model.patientModel.addPatient.AddPatientRequest;
import com.ApiTheBest.ApiMicSiTare.model.patientModel.addPatient.AddPatientResponse;
import com.ApiTheBest.ApiMicSiTare.model.patientModel.getPatient.GetPatientResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

public interface patientController {
    @GetMapping("/display")
    GetPatientResponse getPatients(@RequestParam Optional<Integer> patientId,
                                   @RequestParam Optional<String> phoneNumber,
                                   HttpServletResponse httpServletResponse);

    @PostMapping("/create")
    AddPatientResponse addPatient(@RequestBody @Valid AddPatientRequest addPatientRequest,
                                   HttpServletResponse response);
}


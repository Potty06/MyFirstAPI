package com.ApiTheBest.ApiMicSiTare.controller.adoctorController;

import com.ApiTheBest.ApiMicSiTare.model.doctorModel.addDoctor.AddDoctorRequest;
import com.ApiTheBest.ApiMicSiTare.model.doctorModel.addDoctor.AddDoctorResponse;
import com.ApiTheBest.ApiMicSiTare.model.doctorModel.getDoctor.GetDoctorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

//@Api(value = "display / create")
public interface DoctorController {
    @GetMapping("/display")
    GetDoctorResponse displayDoctor(@RequestParam Optional<Integer> doctorId,
                                            @RequestParam Optional<String> doctorName,
                                            HttpServletResponse httpServletResponse);

    @PostMapping("/create")
    AddDoctorResponse addDoctor(@RequestBody @Valid AddDoctorRequest addDoctorRequest,
                                HttpServletResponse response);
}

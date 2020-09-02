package com.ApiTheBest.ApiMicSiTare.controller.AppointmentController;

import com.ApiTheBest.ApiMicSiTare.model.appointmentModel.getAppointment.GetAppointmentResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Optional;


public interface AppointmentController {

        @GetMapping("/display")
        GetAppointmentResponse displayAppointment(@RequestParam Optional<Integer> appointmentId,
                                                @RequestParam Optional<LocalDate> appointmentDate,
                                                HttpServletResponse httpServletResponse);

//        @PostMapping("/create")
//        AddAppointmentResponse addAppointment(@RequestBody @Valid AddAppointmentRequest addAppointmentRequest,
//                                         HttpServletResponse response);

}

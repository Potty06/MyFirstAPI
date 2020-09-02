package com.ApiTheBest.ApiMicSiTare.controller.AppointmentController;

import com.ApiTheBest.ApiMicSiTare.model.appointmentModel.addAppointment.AddAppointmentRequest;
import com.ApiTheBest.ApiMicSiTare.model.appointmentModel.addAppointment.AddAppointmentResponse;
import com.ApiTheBest.ApiMicSiTare.model.appointmentModel.getAppointment.GetAppointmentResponse;
import com.ApiTheBest.ApiMicSiTare.model.errorModel.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Api(value = " display / create ",
        description = "REST API for Appointment")
public interface AppointmentController {

        @ApiOperation(value = "Get Appointment", nickname = "display")
        @ApiResponses(value = {
                @ApiResponse(code = HttpServletResponse.SC_OK, message = "Result matching criteria", response = GetAppointmentResponse.class),
                @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No entries found", response = GetAppointmentResponse.class),
                @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Invalid request", response = GetAppointmentResponse.class),
                @ApiResponse(code = HttpServletResponse.SC_METHOD_NOT_ALLOWED, message = "Method Not Allowed", response = ErrorResponse.class)
        })

        @GetMapping("/display")
        GetAppointmentResponse displayAppointment(@RequestParam Optional<Integer> appointmentId,
                                                @RequestParam Optional<LocalDate> appointmentDate,
                                                HttpServletResponse httpServletResponse);

        @PostMapping("/create")
        AddAppointmentResponse addAppointment(@RequestBody @Valid AddAppointmentRequest addAppointmentRequest,
                                              HttpServletResponse response);

}

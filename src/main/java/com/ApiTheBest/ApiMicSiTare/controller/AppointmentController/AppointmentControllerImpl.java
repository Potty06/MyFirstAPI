package com.ApiTheBest.ApiMicSiTare.controller.AppointmentController;

import com.ApiTheBest.ApiMicSiTare.controller.adoctorController.DoctorControllerImpl;
import com.ApiTheBest.ApiMicSiTare.controller.apatientController.patientControllerImpl;
import com.ApiTheBest.ApiMicSiTare.model.appointmentModel.Appointment;
import com.ApiTheBest.ApiMicSiTare.model.appointmentModel.getAppointment.GetAppointment;
import com.ApiTheBest.ApiMicSiTare.model.appointmentModel.getAppointment.GetAppointmentResponse;
import com.ApiTheBest.ApiMicSiTare.model.doctorModel.Doctor;
import com.ApiTheBest.ApiMicSiTare.model.patientModel.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/appointment")
@Slf4j

public class AppointmentControllerImpl implements AppointmentController {

    private static List<Appointment> appointments = new ArrayList<>();
    private static Integer appointmentIdValue  = 3;
    //private static DoctorControllerImpl doctorController;
    //private static patientControllerImpl patientController;

    static {
        List<Doctor> doctors = new ArrayList<>();
        //doctors = doctorController.getList();

        List<Patient> patients = new ArrayList<>();
        //patients = patientController.getList();

        appointments.add(new Appointment(1, "Marcel" , "Mirel",
                LocalDate.of(2020, 07, 12),
                LocalTime.now()));
    }

    @Override
    public GetAppointmentResponse displayAppointment(Optional<Integer> appointmentId, Optional<LocalDate> appointmentDate, HttpServletResponse httpServletResponse) {
        //create a response list with doctors
        ArrayList<GetAppointment> responseList = new ArrayList<>();

        log.info("Called: appointment/display");
        log.trace("Called appointment/display with doctorId " + appointmentId + " " + "and the date" + appointmentDate);
        log.debug("Called appointment/display at " + LocalDate.now());

        if(appointmentId.isPresent() && appointmentDate.isEmpty()){
            log.info("appointmentId is present");
            log.debug("appointmentId = " + appointmentId);

            for(Appointment appointment: appointments){
                //if doctor was found, send back response
                if(appointment.getAppointmentId().equals(appointmentId.get())){
                    GetAppointment response = new GetAppointment();
                    //preparing response
                    response.setAppointmentId(appointmentId.get());
                    response.setPatientName(appointment.getPatientName());
                    response.setDoctorName(appointment.getDoctorName());
                    response.setAppointmentDate(appointment.getAppointmentDate());
                    response.setAppointmentTime(appointment.getAppointmentTime());

//                    //add to list
                    log.info("Appointment found and added to the list");
                    log.debug("Appointment found and added " + response.toString());
                    responseList.add(response);
                }
            }
        }
        else if(appointmentId.isEmpty() && appointmentDate.isPresent()){
            log.info("appointmentDate is present");
            log.debug("appointmentDate = " + appointmentDate);

            for(Appointment appointment: appointments) {
                if(appointment.getAppointmentDate().equals(appointmentDate)){
                    GetAppointment response = new GetAppointment();
                    //preparing response
                    response.setAppointmentId(appointment.getAppointmentId());
                    response.setPatientName(appointment.getPatientName());
                    response.setDoctorName(appointment.getDoctorName());
                    response.setAppointmentDate(appointmentDate.get());
                    response.setAppointmentTime(appointment.getAppointmentTime());

                    //add to list
                    log.info("Appointment found and added to the list");
                    log.debug("Appointment found and added " + response.toString());
                    responseList.add(response);
                }
            }
        }
        else{
            //processing
            httpServletResponse.setStatus(400);
            GetAppointmentResponse response = new GetAppointmentResponse();
            response.setResponseDescription("Invalid request");
            return response;

        }
        //check if there any doctors
        if(responseList.isEmpty()){
            //no customers found
            httpServletResponse.setStatus(404);
            GetAppointmentResponse response = new GetAppointmentResponse();
            response.setResponseDescription("No entries found");
            return response;
        }
        //if there are customers, send 200
        else {
            httpServletResponse.setStatus(200);
            GetAppointmentResponse response = new GetAppointmentResponse();
            response.setGetAppointment(responseList);
            response.setResponseDescription("Result matching criteria");
            return response;
        }
    }


//    @Override
//    public AddAppointmentResponse addAppointment(@Valid AddAppointmentRequest addAppointmentRequest, HttpServletResponse response) {
//        return null;
//    }


}

package com.ApiTheBest.ApiMicSiTare.controller.AppointmentController;

import com.ApiTheBest.ApiMicSiTare.controller.Lists;
import com.ApiTheBest.ApiMicSiTare.controller.doctorController.DoctorControllerImpl;
import com.ApiTheBest.ApiMicSiTare.controller.patientController.patientControllerImpl;
import com.ApiTheBest.ApiMicSiTare.model.appointmentModel.Appointment;
import com.ApiTheBest.ApiMicSiTare.model.appointmentModel.addAppointment.AddAppointment;
import com.ApiTheBest.ApiMicSiTare.model.appointmentModel.addAppointment.AddAppointmentRequest;
import com.ApiTheBest.ApiMicSiTare.model.appointmentModel.addAppointment.AddAppointmentResponse;
import com.ApiTheBest.ApiMicSiTare.model.appointmentModel.getAppointment.GetAppointment;
import com.ApiTheBest.ApiMicSiTare.model.appointmentModel.getAppointment.GetAppointmentResponse;
import com.ApiTheBest.ApiMicSiTare.model.doctorModel.Doctor;
import com.ApiTheBest.ApiMicSiTare.model.doctorModel.addDoctor.AddDoctor;
import com.ApiTheBest.ApiMicSiTare.model.doctorModel.addDoctor.AddDoctorResponse;
import com.ApiTheBest.ApiMicSiTare.model.patientModel.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
@Slf4j

public class AppointmentControllerImpl implements AppointmentController {

    private static List<Appointment> appointments = new ArrayList<>();
    private static Integer appointmentIdValue  = 2;
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Patient> patients = new ArrayList<>();
    static {

        doctors = Lists.getDoctors();
        patients = Lists.getPatients();

        appointments.add(new Appointment(1, patients.get(0).getPatientName() , doctors.get(0).getDoctorName(),
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


    @Override
    public AddAppointmentResponse addAppointment(@Valid AddAppointmentRequest addAppointmentRequest, HttpServletResponse response) {

        AddAppointment addAppointment = addAppointmentRequest.getAppointment();

        boolean checkDoctor = false;
        boolean checkPatient = false;
        //check if doctor already exists
        for(Doctor doctor: doctors){
            if(doctor.getDoctorName().equals(addAppointment.getDoctorName())) {
               checkDoctor = true;
            }
        }
        for(Patient patient: patients){
            if(patient.getPatientName().equals(addAppointment.getPatientName())) {
                checkPatient = true;
            }
        }

        if(checkDoctor == false && checkPatient == false){
            log.info("Doctor and patient not exists!");
            log.trace("Doctor with doctor name " + addAppointment.getDoctorName() + " and patient with name: " + addAppointment.getPatientName());
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            AddAppointmentResponse addResponse = new AddAppointmentResponse();
            addResponse.setResponseDescription("Doctor and patient do not exist in the lists!");
            return addResponse;
        }

        else if(checkPatient == false){
            log.info("Patient not exists!");
            log.trace("Patient with patient name " + addAppointment.getPatientName());
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            AddAppointmentResponse addResponse = new AddAppointmentResponse();
            addResponse.setResponseDescription("Patient does not exist in the list, please register!");
            return addResponse;
        }
        else if(checkDoctor == false){
            log.info("Doctor not exists!");
            log.trace("Doctor with doctor name " + addAppointment.getDoctorName());
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            AddAppointmentResponse addResponse = new AddAppointmentResponse();
            addResponse.setResponseDescription("Doctor does not exist in the list, we don't have this doctor in the Hospital!");
            return addResponse;
        }

        //check if appointment already exists
        for(Appointment appointment: appointments){
            if(checkAddAppointment(appointment, addAppointment)){
                log.info("Appointment already exists!");
                log.trace("Appointment with doctor name " + addAppointment.getDoctorName() + ", " + "with Patient name: " +
                        addAppointment.getPatientName()+ ", " + "with Date : " + addAppointment.getAppointmentDate());
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                AddAppointmentResponse addResponse = new AddAppointmentResponse();
                addResponse.setResponseDescription("Appointment already exists!");
                return addResponse;
            }
        }

        //check if appointmentDate and appointmentTime already exists
        for(Appointment appointment: appointments){
            if(checkDateTime(appointment, addAppointment)){
                log.info("Appointment for the chosen doctor, at the specified date and time already exists!");
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                AddAppointmentResponse addResponse = new AddAppointmentResponse();
                addResponse.setResponseDescription("Appointment for the specified date, at the specified date already exists!");
                return addResponse;
            }
        }


        //if not, add him to the list
        log.info("Called /appointment/create");
        log.trace("Called /appointment/create with doctor name " + addAppointment.getDoctorName() + ", " + "with Patient name: " +
                addAppointment.getPatientName()+ ", " + "with Date : " + addAppointment.getAppointmentDate());
        log.debug("Called /appointment/create at " + LocalDate.now());

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(appointmentIdValue);

        appointment.setDoctorName(addAppointment.getDoctorName());
        appointment.setPatientName(addAppointment.getPatientName());
        appointment.setAppointmentDate(addAppointment.getAppointmentDate());
        appointment.setAppointmentTime(addAppointment.getAppointmentTime());

        appointments.add(appointment);
        //send response
        response.setStatus(HttpServletResponse.SC_CREATED);
        AddAppointmentResponse addResponse = new AddAppointmentResponse();
        addResponse.setAppointmentId(appointmentIdValue);
        appointmentIdValue++;
        addResponse.setResponseDescription("Appointment added");
        return addResponse;

    }

    private boolean checkAddAppointment(Appointment appointment, AddAppointment addAppointment){
        if(appointment.getDoctorName().equals(addAppointment.getDoctorName()) &&
                appointment.getPatientName().equals(addAppointment.getPatientName()) &&
                appointment.getAppointmentDate().equals((addAppointment.getAppointmentDate()))){
            return true;
        } else {
            return false;
        }

    }

    //check if there is already an apppointement made for the sepcified doctor, at the specified date and hour
    private boolean checkDateTime(Appointment appointment, AddAppointment addAppointment){
        String doctor = addAppointment.getDoctorName();
        LocalDate date = addAppointment.getAppointmentDate();
        LocalTime hour = addAppointment.getAppointmentTime();
        if (doctor.equals(appointment.getDoctorName()) &&
                date.equals(appointment.getAppointmentDate())&&
                hour.equals(appointment.getAppointmentTime())) {
            return true;
        }else{
            return false;
        }
    }



    //daca nu mai exista in lista data si ora pentru doctorul respectiv

}

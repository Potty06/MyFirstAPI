package com.ApiTheBest.ApiMicSiTare.controller.doctorController;

import com.ApiTheBest.ApiMicSiTare.controller.Lists;
import com.ApiTheBest.ApiMicSiTare.model.doctorModel.Doctor;
import com.ApiTheBest.ApiMicSiTare.model.doctorModel.addDoctor.AddDoctor;
import com.ApiTheBest.ApiMicSiTare.model.doctorModel.addDoctor.AddDoctorRequest;
import com.ApiTheBest.ApiMicSiTare.model.doctorModel.addDoctor.AddDoctorResponse;
import com.ApiTheBest.ApiMicSiTare.model.doctorModel.getDoctor.GetDoctor;
import com.ApiTheBest.ApiMicSiTare.model.doctorModel.getDoctor.GetDoctorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/doctor")
@Slf4j
public class DoctorControllerImpl implements DoctorController{

    private static List<Doctor> doctors = new ArrayList<>();
    private static Integer doctorIdValue  = 3;

    static{
        doctors = Lists.getDoctors();
    }

    @Override
    public GetDoctorResponse displayDoctor(Optional<Integer> doctorId, Optional<String> doctorName, HttpServletResponse httpServletResponse) {

        //create a response list with doctors
        ArrayList<GetDoctor> responseList = new ArrayList<>();

        log.info("Called: doctor/display");
        log.trace("Called doctor/display with doctorId " + doctorId + " " + "and name" + doctorName);
        log.debug("Called doctor/display at " + LocalDate.now());

        if(doctorId.isPresent() && doctorName.isEmpty()){
            log.info("doctorId is present");
            log.debug("doctorId = " + doctorId);

            for(Doctor doctor: doctors){
                //if doctor was found, send back response
                if(doctor.getDoctorId().equals(doctorId.get())){
                    GetDoctor response = new GetDoctor();
                    //preparing response
                    response.setDoctorId(doctorId.get());
                    response.setDoctorName(doctor.getDoctorName());
                    response.setPhoneNo(doctor.getPhoneNo());
                    response.setContract(doctor.getContract());
                    response.setMedicalSpeciality(doctor.getMedicalSpeciality());

                    //add to list
                    log.info("Doctor found and added to the list");
                    log.debug("Doctor found and added " + response.toString());
                    responseList.add(response);
                }
            }
        }
        else if(doctorId.isEmpty() && doctorName.isPresent()){
            log.info("doctorName is present");
            log.debug("doctorName = " + doctorName);

            for(Doctor doctor: doctors) {
                if(doctor.getDoctorName().equals(doctorName.get())){
                    GetDoctor response = new GetDoctor();
                    //preparing response
                    response.setDoctorId(doctor.getDoctorId());
                    response.setDoctorName(doctorName.get());
                    response.setPhoneNo(doctor.getPhoneNo());
                    response.setContract(doctor.getContract());
                    response.setMedicalSpeciality(doctor.getMedicalSpeciality());

                    //add to list
                    log.info("Doctor found and added to the list");
                    log.debug("Doctor found and added " + response.toString());
                    responseList.add(response);
                }
            }
        }
        else{
            //processing
            httpServletResponse.setStatus(400);
            GetDoctorResponse response = new GetDoctorResponse();
            response.setResponseDescription("Invalid request");
            return response;

        }
        //check if there any doctors
        if(responseList.isEmpty()){
            //no customers found
            httpServletResponse.setStatus(404);
            GetDoctorResponse response = new GetDoctorResponse();
            response.setResponseDescription("No entries found");
            return response;
        }
        //if there are customers, send 200
        else {
            httpServletResponse.setStatus(200);
            GetDoctorResponse response = new GetDoctorResponse();
            response.setGetDoctor(responseList);
            response.setResponseDescription("Result matching criteria");
            return response;
        }
    }

    @Override
    public AddDoctorResponse addDoctor(@Valid AddDoctorRequest addDoctorRequest, HttpServletResponse response) {

        AddDoctor addDoctor = addDoctorRequest.getDoctor();

        //check if doctor already exists
        for(Doctor doctor: doctors){
            if(checkAddDoctor(doctor, addDoctor)){
                log.info("Doctor already exists!");
                log.trace("Doctor with name " + addDoctor.getDoctorName() + ", " + "Phone Number: " +
                        addDoctor.getPhoneNo());
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                AddDoctorResponse addResponse = new AddDoctorResponse();
                addResponse.setResponseDescription("Doctor already exists!");
                return addResponse;
            }
        }

        //if not, add him to the list
        log.info("Called /doctor/create");
        log.trace("Called /doctor/create with name " + addDoctor.getDoctorName() + ", " + "Phone Number: " +
                addDoctor.getPhoneNo());
        log.debug("Called /doctor/create at " + LocalDate.now());

        Doctor doctor = new Doctor();
        doctor.setDoctorId(doctorIdValue);

        doctor.setDoctorName(addDoctor.getDoctorName());
        doctor.setContract(addDoctor.getContract());
        doctor.setMedicalSpeciality(addDoctor.getMedicSpeciality());
        doctor.setPhoneNo(addDoctor.getPhoneNo());

        doctors.add(doctor);
        Lists.setDoctors(doctors);
        //send response
        response.setStatus(HttpServletResponse.SC_CREATED);
        AddDoctorResponse addResponse = new AddDoctorResponse();
        addResponse.setdoctorId(doctorIdValue);
        doctorIdValue++;
        addResponse.setResponseDescription("Doctor added");
        return addResponse;

    }

    private boolean checkAddDoctor(Doctor doctor, AddDoctor addDoctor){
        if(doctor.getDoctorName().equals(addDoctor.getDoctorName()) &&
                doctor.getPhoneNo().equals(addDoctor.getPhoneNo())){
            return true;
        } else {
            return false;
        }

    }

}

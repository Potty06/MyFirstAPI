package com.ApiTheBest.ApiMicSiTare.controller.patientController;
import com.ApiTheBest.ApiMicSiTare.model.errorModel.ErrorResponse;
import com.ApiTheBest.ApiMicSiTare.model.patientModel.Patient;
import com.ApiTheBest.ApiMicSiTare.model.patientModel.addPatient.AddPatient;
import com.ApiTheBest.ApiMicSiTare.model.patientModel.addPatient.AddPatientRequest;
import com.ApiTheBest.ApiMicSiTare.model.patientModel.addPatient.AddPatientResponse;
import com.ApiTheBest.ApiMicSiTare.model.patientModel.getPatient.GetPatient;
import com.ApiTheBest.ApiMicSiTare.model.patientModel.getPatient.GetPatientResponse;
import com.ApiTheBest.ApiMicSiTare.model.patientModel.updatePatient.UpdatePatient;
import com.ApiTheBest.ApiMicSiTare.model.patientModel.updatePatient.UpdatePatientRequest;
import com.ApiTheBest.ApiMicSiTare.model.patientModel.updatePatient.UpdatePatientResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class patientControllerImpl implements patientController {

    private Logger log = LoggerFactory.getLogger(patientControllerImpl.class);

    private static List<Patient> patients = new ArrayList<>();
    private static Integer patientIdValue = 4;

    static {

        Patient patient1 = new Patient(1, "John", "LA",
                "john@gmail.com", "0745896358");
        Patient patient2 = new Patient(2, "Dana", "ND",
                "dana@gmail.com", "0769896452");
        Patient patient3 = new Patient(3, "Julia", "AZ",
                "julia@gmail.com", "0765698236");
        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
    }


    @Override
    public GetPatientResponse getPatients(Optional<Integer> patientId,
                                          Optional<String> phoneNumber,
                                          HttpServletResponse httpServletResponse) {


        log.info("Called /getPatient");
        log.trace("/Called /getPatient with patientId= " + patientId + " and phoneNumber = "+ phoneNumber);
        log.debug("Called /getPatient at "+ LocalDate.now());
        //log.info(System.getProperty("user.dir"));

        // only patientId is passed
        if (patientId.isPresent() && phoneNumber.isEmpty()) {
            log.info("patientId is present");
            log.debug("patientId= " + patientId.get());
            //processing
            // create response list
            ArrayList<GetPatient> responseList = new ArrayList<>();
            //patientId is found in the list
            for (Patient patient : patients) {

                //patient found, send back response
                if (patient.getPatientId().equals(patientId.get())) {
                    GetPatient response = new GetPatient();
                    //preparing response
                    response.setPatientId(patientId.get());
                    response.setPatientName(patient.getPatientName());
                    response.setAddress(patient.getAddress());
                    response.setEmail(patient.getEmail());
                    response.setPhoneNo(patient.getPhoneNo());
                    log.info("Patient found and added to the list ");
                    log.debug("Patient found and added: "+ response.toString());
                    //add to list
                    responseList.add(response);
                }
            }
            //check if there any patients in the list
            if (responseList.isEmpty()) {
                log.info("No patients found");
                log.debug("No patients found with the patientId = " + patientId.get());
                //no patients found
                httpServletResponse.setStatus(404);
                GetPatientResponse response = new GetPatientResponse();
                response.setResponseDescription("No entries found");
                return response;
            }
            //if there are patients, send 200
            else {
                log.info("Returning list of patients");
                log.debug("Returning number of " + responseList.size()+ " patients");
                httpServletResponse.setStatus(200);
                GetPatientResponse response = new GetPatientResponse();
                response.setGetPatient(responseList);
                response.setResponseDescription("Result matching criteria");
                return response;
            }
        }
        // just phoneNumber is passed as argument
        else if (patientId.isEmpty() && phoneNumber.isPresent()) {
            //processing
            // create response list
            ArrayList<GetPatient> responseList = new ArrayList<>();
            //patientId is found in the list
            for (Patient patient : patients) {

                //patient found, send back response
                if (patient.getPhoneNo().equals(phoneNumber.get())) {
                    GetPatient response = new GetPatient();
                    //preparing response
                    response.setPatientId(patient.getPatientId());
                    response.setPatientName(patient.getPatientName());
                    response.setAddress(patient.getAddress());
                    response.setEmail(patient.getEmail());
                    response.setPhoneNo(phoneNumber.get());
                    //add to list
                    responseList.add(response);
                }
            }
            //check if there any patients
            if (responseList.isEmpty()) {
                //no patients found
                httpServletResponse.setStatus(404);
                GetPatientResponse response = new GetPatientResponse();
                response.setResponseDescription("No entries found");
                return response;
            }
            //if there are patients, send 200
            else {
                httpServletResponse.setStatus(200);
                GetPatientResponse response = new GetPatientResponse();
                response.setGetPatient(responseList);
                response.setResponseDescription("Result matching criteria");
                return response;
            }
        }
        //error case
        else {
            //processing
            httpServletResponse.setStatus(400); //specify that request is invalid
            GetPatientResponse response = new GetPatientResponse();
            response.setResponseDescription("Invalid request");
            return response;

        }
    }

    @Override
    public AddPatientResponse addPatient(AddPatientRequest addPatientRequest, HttpServletResponse response) {
        AddPatient addPatient = addPatientRequest.getPatient();
        //check if the patient already exists
        for (Patient patient : patients) {
            //if patient already exists, return duplicate status
            if (checkAddPatient(addPatient, patient)) {
//                response.setStatus(409);
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                AddPatientResponse addResponse = new AddPatientResponse();
                addResponse.setResponseDescription("Patient already exists");
                return addResponse;
            }
        }
        //if not, add them to the list
        Patient patient = new Patient();
        patient.setPatientId(patientIdValue);

        patient.setPatientName(addPatient.getPatientName());
        patient.setAddress(addPatient.getAddress());
        patient.setEmail(addPatient.getEmail());
        patient.setPhoneNo(addPatient.getPhoneNo());

        patients.add(patient);
        //send success response
        response.setStatus(HttpServletResponse.SC_CREATED);
        AddPatientResponse addResponse = new AddPatientResponse();
        addResponse.setPatientId(patientIdValue);
        patientIdValue++;
        addResponse.setResponseDescription("Patient added");
        return addResponse;

    }

    private boolean checkAddPatient(AddPatient addPatient, Patient patient) {

        if (addPatient.getPatientName()!=null && patient.getPatientName()!=null){
            if (addPatient.getPatientName().equals(patient.getPatientName())){
                return true;
            }
        }

        return false;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception,
                                                                      HttpServletResponse servletResponse) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorDescription(exception.getMessage());
        servletResponse.setStatus(405);
        return response;
    }
}
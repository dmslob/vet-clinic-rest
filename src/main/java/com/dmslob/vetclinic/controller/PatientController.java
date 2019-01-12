package com.dmslob.vetclinic.controller;

import com.dmslob.vetclinic.entity.Patient;
import com.dmslob.vetclinic.service.PatientService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patients")
public class PatientController {
    private static final Logger LOGGER = LogManager.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAll());
    }

    @RequestMapping(value = "/types/{type}", method = RequestMethod.GET)
    public ResponseEntity<?> getPatients(@PathVariable(value = "type") final String type) {
        return ResponseEntity.ok(patientService.getByType(type));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPatient(@PathVariable(value = "id") final Long id) {
        LOGGER.info("Get Patient by ID = " + id);
        Patient patient = patientService.getById(id);
        if (patient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The patient is not found!");
        }
        return ResponseEntity.ok(patient);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePatient(@PathVariable(value = "id") final Long id) {
        LOGGER.info("Delete Patient by ID = " + id);
        Patient patient = patientService.getById(id);
        if (patient == null) {
            LOGGER.info("The patient is not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The patient is not found!");
        }
        patientService.delete(patient);
        return ResponseEntity.ok().body("The patient was successfully deleted");
    }
}

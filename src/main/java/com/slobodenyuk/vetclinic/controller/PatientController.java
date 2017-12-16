package com.slobodenyuk.vetclinic.controller;

import com.slobodenyuk.vetclinic.entity.Patient;
import com.slobodenyuk.vetclinic.service.DoctorService;
import com.slobodenyuk.vetclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAll());
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPatientsByType(@PathVariable(value = "type") final String type) {
        return ResponseEntity.ok(patientService.getByType(type));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable(value = "id") final Long id) {
        Patient patient = patientService.getById(id);
        if (patient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The patient not found!");
        }
        return ResponseEntity.ok(patient);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePatient(@PathVariable(value = "id") final Long id) {
        Patient patient = patientService.getById(id);
        if (patient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The patient not found!");
        }
        patientService.delete(patient);
        return ResponseEntity.ok().body("The patient was successfully deleted");
    }
}

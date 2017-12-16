package com.slobodenyuk.vetclinic.controller;

import com.slobodenyuk.vetclinic.entity.Doctor;
import com.slobodenyuk.vetclinic.entity.Patient;
import com.slobodenyuk.vetclinic.service.DoctorService;
import com.slobodenyuk.vetclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAll());
    }

    @RequestMapping(value = "/{position}", method = RequestMethod.GET)
    public ResponseEntity<List<Doctor>> getAllFilteredDoctors(@PathVariable(value = "position") final String position) {
        return ResponseEntity.ok(doctorService.getAllByPosition(position));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDoctor(@PathVariable(value = "id") final Long id) {
        Doctor doctor = doctorService.getById(id);
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The doctor not found!");
        }
        return ResponseEntity.ok(doctor);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addDoctor(@RequestParam(value = "name") final String name,
                                       @RequestParam(value = "position") final String position,
                                       @RequestParam(value = "phone") final String phone) {
        Doctor newDoctor = doctorService.add(new Doctor(name, position, phone));
        if (newDoctor == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("New doctor was not created!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("The Doctor was successfully created");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDoctor(@PathVariable(value = "id") final Long id,
                                          @RequestParam(value = "name") final String name,
                                          @RequestParam(value = "position") final String position,
                                          @RequestParam(value = "phone") final String phone) {
        Doctor doctor = doctorService.getById(id);
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The doctor not found!");
        }
        doctor.setName(name);
        doctor.setPosition(position);
        doctor.setPhone(phone);
        Doctor updatedDoctor = doctorService.update(doctor);
        if (updatedDoctor == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update error!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("The Doctor was successfully updated");
    }

    @RequestMapping(value = "/{id}/patients", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPatientsByDoctor(@PathVariable(value = "id") final Long id) {
        Doctor doctor = doctorService.getById(id);
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The doctor not found!");
        }
        return ResponseEntity.ok(patientService.getByDoctor(doctor));
    }

    @RequestMapping(value = "/{doctorId}/patients", method = RequestMethod.POST)
    public ResponseEntity<?> addPatient(@PathVariable(value = "doctorId") final Long doctorId,
                                        @RequestParam(value = "name") final String name,
                                        @RequestParam(value = "age") final Integer age,
                                        @RequestParam(value = "type") final String type,
                                        @RequestParam(value = "illness") final Boolean illness) {

        Doctor doctor = doctorService.getById(doctorId);
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The doctor not found!");
        }
        Patient patient = new Patient(name, age, type, illness);
        patient.setDoctor(doctor);
        Patient newPatient = patientService.add(patient);
        if (newPatient == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("New patient was not created!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Patient was successfully created");
    }
}

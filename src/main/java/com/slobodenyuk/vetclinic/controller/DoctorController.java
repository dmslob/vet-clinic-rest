package com.slobodenyuk.vetclinic.controller;

import com.slobodenyuk.vetclinic.dto.DoctorDto;
import com.slobodenyuk.vetclinic.entity.Doctor;
import com.slobodenyuk.vetclinic.entity.Patient;
import com.slobodenyuk.vetclinic.service.DoctorService;
import com.slobodenyuk.vetclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<List<Doctor>> getDoctors() {
        return ResponseEntity.ok(doctorService.getAll());
    }

    @RequestMapping(value = "/{position}", method = RequestMethod.GET)
    public ResponseEntity<List<Doctor>> getDoctors(@PathVariable(value = "position") final String position) {
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

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addDoctor(@RequestBody DoctorDto doctorDto) {
        Doctor doctor = doctorService.add(new Doctor(doctorDto.getName(), doctorDto.getPosition(), doctorDto.getPhone()));
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("New doctor was not created!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("The Doctor was successfully created");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDoctor(@RequestBody DoctorDto doctorDto) {
        Doctor doctor = doctorService.getById(doctorDto.getId());
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The doctor not found!");
        }
        doctor.setName(doctorDto.getName());
        doctor.setPosition(doctorDto.getPosition());
        doctor.setPhone(doctorDto.getPhone());
        Doctor updatedDoctor = doctorService.update(doctor);
        if (updatedDoctor == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update error!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("The Doctor was successfully updated");
    }

    @RequestMapping(value = "/{id}/patients", method = RequestMethod.GET)
    public ResponseEntity<?> getPatientsOfDoctor(@PathVariable(value = "id") final Long id) {
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

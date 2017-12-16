package com.slobodenyuk.vetclinic.service;

import com.slobodenyuk.vetclinic.dao.PatientDao;
import com.slobodenyuk.vetclinic.entity.Doctor;
import com.slobodenyuk.vetclinic.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientService {
    @Autowired
    private PatientDao patientDao;

    public List<Patient> getAll() {
        return patientDao.findAll();
    }

    public Patient getById(final Long id) {
        return patientDao.findOne(id);
    }

    public List<Patient> getByType(final String type) {
        return patientDao.findByType(type);
    }

    public Patient add(Patient patient) {
        return patientDao.add(patient);
    }

    public void delete(final Patient patient) {
        patientDao.delete(patient);
    }

    public List<Patient> getByDoctor(Doctor doctor) {
        return patientDao.getByDoctor(doctor);
    }
}

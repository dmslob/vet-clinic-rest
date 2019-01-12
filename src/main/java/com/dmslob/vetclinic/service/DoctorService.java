package com.dmslob.vetclinic.service;

import com.dmslob.vetclinic.dao.DoctorDao;
import com.dmslob.vetclinic.entity.Doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DoctorService {
    @Autowired
    private DoctorDao doctorDao;

    public List<Doctor> getAll() {
        return doctorDao.findAll();
    }

    public List<Doctor> getAllByPosition(String position) {
        return doctorDao.findAllByPosition(position);
    }

    public Doctor add(Doctor doc) {
        return doctorDao.add(doc);
    }

    public Doctor update(Doctor doc) {
        return doctorDao.update(doc);
    }

    public Doctor getById(Long id) {
        return doctorDao.findOne(id);
    }
}

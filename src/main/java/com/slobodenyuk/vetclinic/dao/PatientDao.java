package com.slobodenyuk.vetclinic.dao;

import com.slobodenyuk.vetclinic.entity.Doctor;
import com.slobodenyuk.vetclinic.entity.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class PatientDao extends AbstractDao<Patient> {
    public List<Patient> getByDoctor(Doctor doctor) {
        return getCurrentSession().createQuery("from Patient p where p.doctor=:doc")
                .setParameter("doc", doctor)
                .list();
    }

    public List<Patient> findByType(String type) {
        return getCurrentSession().createQuery("from Patient p where p.type=:type")
                .setParameter("type", type)
                .list();
    }

    @Override
    protected Class<Patient> getGenericType() {
        return Patient.class;
    }
}

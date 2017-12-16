package com.slobodenyuk.vetclinic.dao;

import com.slobodenyuk.vetclinic.entity.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class DoctorDao extends AbstractDao<Doctor> {
    public List<Doctor> findAllByPosition(String position) {
        return getCurrentSession().createQuery("from Doctor d where d.position=:pos")
                .setString("pos", position)
                .list();
    }

    @Override
    protected Class<Doctor> getGenericType() {
        return Doctor.class;
    }
}

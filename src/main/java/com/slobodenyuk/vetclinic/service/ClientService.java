package com.slobodenyuk.vetclinic.service;

import com.slobodenyuk.vetclinic.dao.ClientDao;
import com.slobodenyuk.vetclinic.entity.Client;
import com.slobodenyuk.vetclinic.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientService {
    @Autowired
    private ClientDao clientDao;

    public List<Client> getAll() {
        return clientDao.findAll();
    }

    public Client add(Client client) {
        return clientDao.add(client);
    }

    public void addPatient(final Client client, final Patient patient) {
        client.getPets().add(patient);
        update(client);
    }

    public Client update(Client client) {
        return clientDao.update(client);
    }

    public Client getById(Long id) {
        return clientDao.findOne(id);
    }

    public void delete(Long id) {
        clientDao.delete(id);
    }
}

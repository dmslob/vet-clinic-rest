package com.slobodenyuk.vetclinic.dao;

import com.slobodenyuk.vetclinic.entity.Client;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class ClientDao extends AbstractDao<Client> {
    @Override
    protected Class<Client> getGenericType() {
        return Client.class;
    }
}

package com.dmslob.vetclinic.dao;

import com.dmslob.vetclinic.entity.Client;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class ClientDao extends AbstractDao<Client> {
    @Override
    protected Class<Client> getGenericType() {
        return Client.class;
    }
}

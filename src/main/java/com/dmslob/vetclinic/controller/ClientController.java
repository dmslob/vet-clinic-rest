package com.dmslob.vetclinic.controller;

import com.dmslob.vetclinic.dto.ClientDto;
import com.dmslob.vetclinic.entity.Client;
import com.dmslob.vetclinic.entity.Patient;
import com.dmslob.vetclinic.service.ClientService;
import com.dmslob.vetclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PatientService patientService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.ok(clientService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getClient(@PathVariable(value = "id") final Long id) {
        Client client = clientService.getById(id);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The client is not found!");
        }
        return ResponseEntity.ok(client);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") final Long id) {
        Client client = clientService.getById(id);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The client is not found!");
        }
        clientService.delete(id);
        return ResponseEntity.ok().body("The client was successfully deleted");
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addClient(@RequestBody ClientDto clientDto) {
        if (clientDto.getName().isEmpty() || clientDto.getPhone().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The name or phone can't be empty");
        }
        Client client = new Client(clientDto.getName(), clientDto.getPhone());
        Client newClient = clientService.add(client);
        if (newClient == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("New client was not created!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Client was successfully created");
    }

    @RequestMapping(value = "/{clientId}/patients/{patientId}", method = RequestMethod.POST)
    public ResponseEntity<?> addPatient(@PathVariable(value = "clientId") final Long clientId,
                                        @PathVariable(value = "patientId") final Long patientId) {
        Client client = clientService.getById(clientId);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The client is not found!");
        }
        Patient patient = patientService.getById(patientId);
        if (patient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The patient is not found!");
        }
        clientService.addPatient(client, patient);
        return ResponseEntity.status(HttpStatus.CREATED).body("The patient was successfully created");
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateClient(@RequestBody ClientDto clientDto) {
        Client client = clientService.getById(clientDto.getId());
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The client is not found!");
        }
        client.setName(clientDto.getName());
        client.setPhone(clientDto.getPhone());
        Client updatedClient = clientService.update(client);
        if (updatedClient == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update error!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("The Client was successfully updated");
    }
}

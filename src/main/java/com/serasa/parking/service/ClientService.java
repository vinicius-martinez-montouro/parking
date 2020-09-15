package com.serasa.parking.service;

import com.serasa.parking.dto.ClientDTO;
import com.serasa.parking.model.Client;

import java.util.List;

/**
 * @author vinicius.montouro
 */
public interface ClientService {
    /**
     * Find all clients
     * @return clients list
     */
    List<Client> findAll();

    /**
     * Search Client by cpf
     * @param id
     * @return Client
     */
    Client findById(String id);

    /**
     * Save Client
     * @param client
     */
    void saveClient(Client client);

    /**
     * Update Client
     * @param client
     */
    void updateClient(Client client);

    /**
     * Delete client by id
     * @param id
     */
    void deleteClientById(String id);

    /**
     * Convert DTO in model
     * @param clientDTO
     * @return
     */
    Client fromDTO(ClientDTO clientDTO);
}

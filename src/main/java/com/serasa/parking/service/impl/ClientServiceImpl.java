package com.serasa.parking.service.impl;

import com.serasa.parking.dto.ClientDTO;
import com.serasa.parking.model.Client;
import com.serasa.parking.model.Stay;
import com.serasa.parking.model.Vehicle;
import com.serasa.parking.repository.ClientRepository;
import com.serasa.parking.repository.VehicleRepository;
import com.serasa.parking.service.ClientService;
import com.serasa.parking.service.VehicleService;
import com.serasa.parking.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(String id) {
        return clientRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.insert(client);
    }

    @Override
    public void updateClient(Client client) {
        Client newClinet = clientRepository.findById(client.getCpf())
                .map(obj ->
                        Client.builder()
                                .cpf(obj.getCpf())
                                .name(client.getName())
                                .birthDate(client.getBirthDate())
                                .tel(client.getTel())
                                .email(client.getEmail()).build()).get();
        clientRepository.save(newClinet);
    }

    @Override
    public void deleteClientById(String id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client fromDTO(ClientDTO clientDTO) {
        return
                Client.builder()
                .cpf(clientDTO.getCpf())
                .name(clientDTO.getName())
                .birthDate(clientDTO.getBirthDate())
                .tel(clientDTO.getTel())
                .email(clientDTO.getEmail())
                .build();
    }
}

package com.serasa.parking.service.impl;

import com.mongodb.DuplicateKeyException;
import com.serasa.parking.dto.VehicleDTO;
import com.serasa.parking.model.Client;
import com.serasa.parking.model.Vehicle;
import com.serasa.parking.repository.ClientRepository;
import com.serasa.parking.repository.VehicleRepository;
import com.serasa.parking.service.VehicleService;
import com.serasa.parking.service.exception.ObjectDuplicateException;
import com.serasa.parking.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vinicius.montouro
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> findByColor(String color) {
        return vehicleRepository.findByColor(color);
    }

    @Override
    public List<Vehicle> findByYearFabrication(Integer yearFabrication) {
        return vehicleRepository.findByYearFabrication(yearFabrication);
    }

    @Override
    public List<Vehicle> findByBrand(String brand) {
        return vehicleRepository.findByBrand(brand);
    }

    @Override
    public List<Vehicle> findByModel(String model) {
        return vehicleRepository.findByModel(model);
    }

    @Override
    public Vehicle findById(String board) {
        return vehicleRepository.findById(board).orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {
        try{
            Client client = clientRepository
                    .findById(vehicle
                            .getClient()
                            .getCpf())
                    .orElseThrow(() -> new ObjectNotFoundException("Object not found"));
            vehicle.setClient(client);
            vehicleRepository.insert(vehicle);
        }catch(DuplicateKeyException e){
            throw new ObjectDuplicateException("Object exist in database");
        }
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        Client client = clientRepository
                .findById(vehicle
                        .getClient()
                        .getCpf())
                .orElseThrow(() -> new ObjectNotFoundException("Object not found"));
        vehicle.setClient(client);
        Vehicle newVehicle = vehicleRepository.findById(vehicle.getBoard())
                                .map(obj ->
                                        Vehicle
                                                .builder()
                                                .board(obj.getBoard())
                                                .brand(vehicle.getBrand())
                                                .model(vehicle.getModel())
                                                .yearFabrication(vehicle.getYearFabrication())
                                                .color(vehicle.getColor())
                                                .client(vehicle.getClient()).build()).get();
        vehicleRepository.save(newVehicle);
    }

    @Override
    public void deleteVehicleById(String board) {
        vehicleRepository.deleteById(board);
    }

    @Override
    public Vehicle fromDTO(VehicleDTO vehicleDTO) {
        return Vehicle.builder()
                .client(Client.builder().cpf(vehicleDTO.getCpfClient()).build())
                .color(vehicleDTO.getColor())
                .yearFabrication(vehicleDTO.getYearFabrication())
                .brand(vehicleDTO.getBrand())
                .model(vehicleDTO.getModel())
                .board(vehicleDTO.getBoard())
                .build();
    }
}

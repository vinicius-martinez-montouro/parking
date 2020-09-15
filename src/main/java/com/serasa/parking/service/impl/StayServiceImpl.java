package com.serasa.parking.service.impl;

import com.mongodb.DuplicateKeyException;
import com.serasa.parking.dto.StayDTO;
import com.serasa.parking.dto.StayVehicleInDTO;
import com.serasa.parking.model.Stay;
import com.serasa.parking.model.Vehicle;
import com.serasa.parking.repository.StayRepository;
import com.serasa.parking.repository.VehicleRepository;
import com.serasa.parking.service.StayService;
import com.serasa.parking.service.exception.ObjectDuplicateException;
import com.serasa.parking.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author vinicius.montouro
 */
@Service
public class StayServiceImpl implements StayService {

    @Autowired
    private StayRepository stayRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Stay> findAll() {
        return stayRepository.findAll();
    }

    @Override
    public Stay findById(Long id) {
        return stayRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    @Override
    public void saveStay(Stay stay) {
        Vehicle vehicle = vehicleRepository
                .findById(stay
                        .getVehicle()
                        .getBoard())
                .orElseThrow(() -> new ObjectNotFoundException("Object not found"));
        stay.setVehicle(vehicle);
        stayRepository.insert(stay);
    }

    @Override
    public void updateStay(Stay stay) {
        Vehicle vehicle = vehicleRepository
                .findById(stay
                        .getVehicle()
                        .getBoard())
                .orElseThrow(() -> new ObjectNotFoundException("Object not found"));
        stay.setVehicle(vehicle);
        Stay newStay = stayRepository.findById(stay.getId())
                .map(obj ->
                        Stay.builder()
                        .dateInput(stay.getDateInput())
                        .dateOutput(stay.getDateOutput())
                        .vehicle(stay.getVehicle())
                        .id(obj.getId())
                        .build()).get();

        stayRepository.save(newStay);
    }

    @Override
    public void deleteStayById(Long id) {
        stayRepository.deleteById(id);
    }

    @Override
    public Stay fromDTO(StayDTO stayDTO) {
        return Stay.builder()
                .id(stayDTO.getId())
                .dateOutput(stayDTO.getDateOutput())
                .dateInput(stayDTO.getDateInput())
                .vehicle(Vehicle.builder().board(stayDTO.getVehicleBoard()).build())
                .build();
    }

    @Override
    public Stay vehicleInParking(StayVehicleInDTO stayVehicleInDTO, Stay stay) {
        Vehicle vehicle = vehicleRepository.findById(stayVehicleInDTO.getVehicleBoard())
                .orElseThrow(() -> new ObjectNotFoundException("Object not found"));
        stay.setVehicle(vehicle);
        stay.setDateInput(new Date());
        return stayRepository.insert(stay);
    }

    @Override
    public void vehicleOutParking(StayVehicleInDTO stayVehicleInDTO) {
        Vehicle vehicle = vehicleRepository.findById(stayVehicleInDTO.getVehicleBoard())
                .orElseThrow(() -> new ObjectNotFoundException("Object not found"));
        List<Stay> stays = new ArrayList<>();
        stays.addAll(stayRepository.findByVehicleAndOutStayFalse(vehicle));
        stays.stream().forEach(stay -> {
            stay.setDateOutput(new Date());
            stay.setOutStay(true);
        });
        stayRepository.saveAll(stays);
    }

    @Override
    public Long countByOutStayFalse(){
        return stayRepository.countByOutStayFalse();
    }
}

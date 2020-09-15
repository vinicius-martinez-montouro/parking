package com.serasa.parking.service;

import com.serasa.parking.dto.VehicleDTO;
import com.serasa.parking.model.Vehicle;

import java.util.List;

/**
 * @author vinicius.montouro
 */
public interface VehicleService {
    /**
     * Find all vehicles
     * @return vehicles list
     */
    List<Vehicle> findAll();

    /**
     * Search Vehicles by Color
     * @param color
     * @return Vehicles List
     */
    List<Vehicle> findByColor(String color);

    /**
     * Search Vehicles by Year Fabrication
     * @param yearFabrication
     * @return Vehicles List
     */
    List<Vehicle> findByYearFabrication(Integer yearFabrication);

    /**
     * Search Vehicles by Brand
     * @param brand
     * @return Vehicles List
     */
    List<Vehicle> findByBrand(String brand);

    /**
     * Search Vehicles by model
     * @param model
     * @return Vehicles List
     */
    List<Vehicle> findByModel(String model);

    /**
     * Search Vehicle by board
     * @param board
     * @return Vehicle
     */
    Vehicle findById(String board);

    /**
     * Save Vehicle
     * @param vehicle
     */
    void saveVehicle(Vehicle vehicle);

    /**
     * Update Vehicle
     * @param vehicle
     */
    void updateVehicle(Vehicle vehicle);


    /**
     * Delete vehicle by id
     * @param board
     */
    void deleteVehicleById(String board);

    /**
     * Convert VehicleDTO in Vehicle
     * @param vehicleDTO
     * @return
     */
    Vehicle fromDTO(VehicleDTO vehicleDTO);
}

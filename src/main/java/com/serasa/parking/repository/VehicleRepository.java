package com.serasa.parking.repository;

import com.serasa.parking.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author vinicius.montouro
 * Class repository of Vehicle
 */
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
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
}

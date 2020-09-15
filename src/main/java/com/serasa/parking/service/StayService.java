package com.serasa.parking.service;

import com.serasa.parking.dto.StayDTO;
import com.serasa.parking.dto.StayVehicleInDTO;
import com.serasa.parking.dto.VehicleDTO;
import com.serasa.parking.model.Stay;
import com.serasa.parking.model.Vehicle;

import java.util.List;

/**
 * @author vinicius.montouro
 */
public interface StayService {
    /**
     * Find all stays
     * @return Stays list
     */
    List<Stay> findAll();

    /**
     * Search Stay by id
     * @param id
     * @return Stay List
     */
    Stay findById(Long id);

    /**
     * SaveStay
     * @param stay
     */
    void saveStay(Stay stay);

    /**
     * Update Stay
     * @param stay
     */
    void updateStay(Stay stay);

    /**
     * Delete stay by id
     * @param id
     */
    void deleteStayById(Long id);

    /**
     * Convert DTO in Model
     * @param stayDTO
     * @return
     */
    Stay fromDTO(StayDTO stayDTO);

    /**
     * Register in Vehile in the Parking
     * @param stayVehicleInDTO
     * @return
     */
    Stay vehicleInParking(StayVehicleInDTO stayVehicleInDTO, Stay stay);

    /**
     * Register vehicle exit in the parkint
     * @param stayVehicleInDTO
     */
    void vehicleOutParking(StayVehicleInDTO stayVehicleInDTO);

    /**
     * Count Vehicles in Parking
     * @return
     */
    Long countByOutStayFalse();
}

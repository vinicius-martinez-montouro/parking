package com.serasa.parking.repository;

import com.serasa.parking.model.Stay;
import com.serasa.parking.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author vinicius.montouro
 * Class repository of Stay
 */
public interface StayRepository extends MongoRepository<Stay, Long> {
    /**
     * Search Stay by vehicle
     * @param vehicle
     * @return Stay
     */
    List<Stay> findByVehicleAndOutStayFalse(Vehicle vehicle);

    /**
     * Count Vehicles in Parking
     * @return
     */
    Long countByOutStayFalse();
}

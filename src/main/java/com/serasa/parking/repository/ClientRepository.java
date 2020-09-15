package com.serasa.parking.repository;

import com.serasa.parking.model.Client;
import com.serasa.parking.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author vinicius.montouro
 * Class repository of Client
 */
public interface ClientRepository extends MongoRepository<Client, String> {
}

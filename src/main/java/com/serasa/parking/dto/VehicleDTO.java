package com.serasa.parking.dto;


import com.serasa.parking.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vinicius.montouro
 * Class DTO of Vehicle
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleDTO {

    private String board;

    private String brand;

    private String model;

    private Integer yearFabrication;

    private String color;

    private String cpfClient;

    public VehicleDTO(Vehicle vehicle){
        this.board = vehicle.getBoard();
        this.brand = vehicle.getBrand();
        this.model = vehicle.getModel();
        this.yearFabrication = vehicle.getYearFabrication();
        this.color = vehicle.getColor();
        this.cpfClient = vehicle.getClient() != null ? vehicle.getClient().getCpf() : "";
    }
}

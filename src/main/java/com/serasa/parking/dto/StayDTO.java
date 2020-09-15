package com.serasa.parking.dto;

import com.serasa.parking.model.Stay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author vinicius.montouro
 * Class DTO of Stay
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StayDTO {

    private Long id;

    private Date dateInput;

    private Date dateOutput;

    private String vehicleBoard;

    public StayDTO(Stay stay){
        this.id = stay.getId();
        this.dateInput = stay.getDateInput();
        this.dateOutput = stay.getDateOutput();
        this.vehicleBoard = stay.getVehicle() != null ? stay.getVehicle().getBoard() : "";
    }
}

package com.serasa.parking.resource;

import com.serasa.parking.dto.VehicleDTO;
import com.serasa.parking.model.Vehicle;
import com.serasa.parking.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author vinicius.montouro
 */
@RestController
@RequestMapping("parking/v1/vehicle")
public class VehicleResource {

    /**
     * Object service vehicle
     */
    @Autowired
    private VehicleService vehicleService;

    /**
     * Save vehicle
     * @param vehicleDTO
     * @return
     */
    @Operation(summary = "Include a new Vehicle")
    @PostMapping
    public ResponseEntity<Void> saveVehicle(@RequestBody VehicleDTO vehicleDTO){
        Vehicle vehicle = vehicleService.fromDTO(vehicleDTO);
        vehicleService.saveVehicle(vehicle);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{board}").buildAndExpand(vehicle.getBoard()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Find Vehicle by board
     * @param board
     * @return
     */
    @Operation(summary = "Get a vehicle by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the vehicle",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VehicleDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Vehicle not found",
                    content = @Content) })
    @GetMapping("/{board}")
    public ResponseEntity<VehicleDTO> findByBoard(@Parameter(description = "id of vehicle to be searched")
                                   @PathVariable String board){
        return ResponseEntity.ok().body(new VehicleDTO(vehicleService.findById(board)));
    }

    /**
     * Find all vehicles
     * @return vehicles list
     */
    @Operation(summary = "Get all Vehicles")
    @GetMapping
    public ResponseEntity<List<VehicleDTO>> findAll(){
        return ResponseEntity.ok()
                .body(vehicleService
                        .findAll()
                        .stream()
                        .map(vehicle -> new VehicleDTO(vehicle)).collect(Collectors.toList()));
    }


    /**
     * Update Vehicle
     * @param vehicleDTO
     */
    @Operation(summary = "Change a Vehicle")
    @PutMapping
    public ResponseEntity<Void> updateVehicle(VehicleDTO vehicleDTO){
        Vehicle vehicle = vehicleService.fromDTO(vehicleDTO);
        vehicleService.saveVehicle(vehicle);
        vehicleService.updateVehicle(vehicle);
        return ResponseEntity.noContent().build();
    }


    /**
     * Delete vehicle by id
     * @param board
     */
    @Operation(summary = "Deleted a Vehicle")
    @DeleteMapping("/{board}")
    public ResponseEntity<Void> deleteVehicleById(@Parameter(description = "id of vehicle to be deletd")
                                      @PathVariable String board){
        vehicleService.deleteVehicleById(board);
        return ResponseEntity.noContent().build();
    }

}

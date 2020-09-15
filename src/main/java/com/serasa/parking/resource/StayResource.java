package com.serasa.parking.resource;

import com.serasa.parking.dto.StayDTO;
import com.serasa.parking.dto.StayVehicleInDTO;
import com.serasa.parking.dto.VehicleDTO;
import com.serasa.parking.model.Stay;
import com.serasa.parking.service.StayService;
import com.serasa.parking.service.sequence.SequenceGeneratorService;
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
@RequestMapping("parking/v1/stay")
public class StayResource {

    /**
     * Object service stay
     */
    @Autowired
    private StayService stayService;

    /**
     * Generate sequence to id entity
     */
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    /**
     * Save stay
     * @param stayDTO
     * @return
     */
    @Operation(summary = "Include a new Stay")
    @PostMapping
    public ResponseEntity<Void> saveStay(@RequestBody StayDTO stayDTO){
        Stay stay = stayService.fromDTO(stayDTO);
        stay.setId(sequenceGeneratorService.generateSequence(Stay.SEQUENCE_STAY));
        stayService.saveStay(stay);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{board}").buildAndExpand(stay.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Incluse vehicle in Parking
     * @param vehicleDTO
     * @return
     */
    @Operation(summary = "Include vehicle in Parking")
    @PostMapping(value = "/in")
    public ResponseEntity<Void> vehicleInParking(@RequestBody StayVehicleInDTO vehicleDTO){
        Stay stay =
                stayService.vehicleInParking(vehicleDTO,
                        Stay.builder().id(sequenceGeneratorService.generateSequence(Stay.SEQUENCE_STAY)).build());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{board}").buildAndExpand(stay.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Out Vehicle in Parking
     * @param stayVehicleInDTO
     * @return
     */
    @Operation(summary = "Out vehicle in Parking")
    @PutMapping(value = "/out")
    public ResponseEntity<Void> vehicleOutParking(@RequestBody StayVehicleInDTO stayVehicleInDTO){
        stayService.vehicleOutParking(stayVehicleInDTO);
        return ResponseEntity.noContent().build();
    }


    /**
     * Find Stay by id
     * @param id
     * @return
     */
    @Operation(summary = "Get a stay by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the stay",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StayDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Stay not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<StayDTO> findById(@Parameter(description = "id of stay to be searched")
                                   @PathVariable Long id){
        return ResponseEntity.ok().body(new StayDTO(stayService.findById(id)));
    }


    /**
     * Find all stays
     * @return Stays list
     */
    @Operation(summary = "Get all Stays")
    @GetMapping
    public ResponseEntity<List<StayDTO>> findAll(){
        return ResponseEntity.ok()
                .body(stayService
                        .findAll()
                        .stream()
                        .map(stay -> new StayDTO(stay))
                        .collect(Collectors.toList()));
    }


    /**
     * Update Stay
     * @param stayDTO
     */
    @Operation(summary = "Change a Stay")
    @PutMapping
    public ResponseEntity<Void> updateStay(StayDTO stayDTO){
        Stay stay = stayService.fromDTO(stayDTO);
        stayService.saveStay(stay);
        stayService.updateStay(stay);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete stay by id
     * @param id
     */
    @Operation(summary = "Deleted a stay by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStayById(@Parameter(description = "id of stay to be deletd")
                                      @PathVariable Long id){
        stayService.deleteStayById(id);
        return ResponseEntity.noContent().build();
    }
}

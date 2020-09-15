package com.serasa.parking.resource;

import com.serasa.parking.dto.ClientDTO;
import com.serasa.parking.model.Client;
import com.serasa.parking.service.ClientService;
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
@RequestMapping("/client")
public class ClientResource {

    /**
     * Object service client
     */
    @Autowired
    private ClientService clientService;

    /**
     * Save client
     * @param clientDTO
     * @return
     */
    @Operation(summary = "Include a new Client")
    @PostMapping
    public ResponseEntity<Void> saveClient(@RequestBody ClientDTO clientDTO){
        Client client = clientService.fromDTO(clientDTO);
        clientService.saveClient(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cpf}").buildAndExpand(client.getCpf()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Find client by cpf
     * @param cpf
     * @return
     */
    @Operation(summary = "Get a Client by its cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the client",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Client not found",
                    content = @Content) })
    @GetMapping("/{cpf}")
    public ResponseEntity<ClientDTO> findByBoard(@Parameter(description = "cpf of client to be searched")
                                   @PathVariable String cpf){
        return ResponseEntity.ok().body(new ClientDTO(clientService.findById(cpf)));
    }

    /**
     * Find all clients
     * @return clients list
     */
    @Operation(summary = "Get all Clients")
    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        return ResponseEntity.ok()
                .body(clientService
                        .findAll()
                        .stream()
                        .map(client -> new ClientDTO(client)).collect(Collectors.toList()));

    }

    /**
     * Update Client
     * @param clientDTO
     */
    @Operation(summary = "Change a Client")
    @PutMapping
    public ResponseEntity<Void> updateClient(ClientDTO clientDTO){
        Client client = clientService.fromDTO(clientDTO);
        clientService.saveClient(client);
        clientService.updateClient(client);
        return ResponseEntity.noContent().build();
    }


    /**
     * Delete client by cpf
     * @param cpf
     */
    @Operation(summary = "Deleted a Client by cpf")
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteClientById(@Parameter(description = "cpf of client to be deletd")
                                      @PathVariable String cpf){
        clientService.deleteClientById(cpf);
        return ResponseEntity.noContent().build();
    }

}

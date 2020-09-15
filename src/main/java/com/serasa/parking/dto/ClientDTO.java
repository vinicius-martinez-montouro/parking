package com.serasa.parking.dto;

import com.serasa.parking.model.Client;
import com.serasa.parking.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author vinicius.montouro
 * Class DTO of Client
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private String cpf;

    private String name;

    private Date birthDate;

    private String tel;

    private String email;

    public ClientDTO(Client client){
        this.cpf = client.getCpf();
        this.name = client.getName();
        this.birthDate = client.getBirthDate();
        this.tel = client.getTel();
        this.email = client.getEmail();
    }
}

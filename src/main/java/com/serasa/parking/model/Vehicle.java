package com.serasa.parking.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author vinicius.montouro
 * Class model of Vehicle
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class Vehicle {

    @Id
    @EqualsAndHashCode.Include
    private String board;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    private Integer yearFabrication;

    @NotBlank
    private String color;

    @NotNull
    private Client client;
}

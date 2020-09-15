package com.serasa.parking.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author vinicius.montouro
 * Class model of Client
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collation = "clients")
public class Client {

    @Id
    @EqualsAndHashCode.Include
    private String cpf;

    @NotBlank
    private String name;

    @NotBlank
    private Date birthDate;

    @NotBlank
    private String tel;

    @Email
    private String email;

}

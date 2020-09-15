package com.serasa.parking.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author vinicius.montouro
 * Class model of Stay
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class Stay {

    @Transient
    public static final String SEQUENCE_STAY = "stays_sequence";

    @Id
    private Long id;

    private Date dateInput;

    private Date dateOutput;

    private Vehicle vehicle;

    private boolean outStay = false;

}

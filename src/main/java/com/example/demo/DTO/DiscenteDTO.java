package com.example.demo.DTO;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class DiscenteDTO {

    private Long id;
    private String nome;
    private String cognome;
    private Date dataDiNascita;
    private String cittaDiResidenza;
    private int voto;
    private List<CorsoDTO> corsi;
}

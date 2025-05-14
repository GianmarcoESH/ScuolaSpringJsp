package com.example.demo.mapper;

import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Discente;
import org.springframework.stereotype.Component;

@Component
public class DiscenteConverter {

    public Discente fromDtoToEntity(DiscenteDTO discenteDTO){
        Discente discente = new Discente();
        discente.setId(discente.getId());
        discente.setNome(discenteDTO.getNome());
        discente.setCognome(discenteDTO.getCognome());
        discente.setCittaDiResidenza(discenteDTO.getCittaDiResidenza());
        discente.setDataDiNascita(discenteDTO.getDataDiNascita());
        discente.setVoto(discenteDTO.getVoto());

        return discente;
    }


    public DiscenteDTO fromEntityToDto (Discente discente){
        DiscenteDTO discenteDTO = new DiscenteDTO();
        discenteDTO.setId(discente.getId());
        discenteDTO.setNome(discente.getNome());
        discenteDTO.setCognome(discente.getCognome());
        discenteDTO.setCittaDiResidenza(discente.getCittaDiResidenza());
        discenteDTO.setDataDiNascita(discente.getDataDiNascita());
        discenteDTO.setVoto(discente.getVoto());
        return discenteDTO;
    }
}

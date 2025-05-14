package com.example.demo.mapper;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.entity.Corso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CorsoConverter {

    @Autowired
    DocenteConverter docenteConverter;


    public CorsoDTO fromEntityToDto(Corso corso){
        CorsoDTO corsoDTO = new CorsoDTO();

        corsoDTO.setId(corso.getId());
        corsoDTO.setNomeCorso(corso.getNomeCorso());
        corsoDTO.setOreCorso(corso.getOreCorso());
        corsoDTO.setAnnoAccademico(corso.getAnnoAccademico());

        if(corso.getDocente() != null){
            corsoDTO.setDocente(docenteConverter.fromEntityToDto(corso.getDocente()));
        }else{
            corsoDTO.setDocente(null);
        }


        return corsoDTO;
    }

    public Corso fromDtoToEntity(CorsoDTO corsoDTO){
        Corso corso = new Corso();
        corso.setId(corsoDTO.getId());
        corso.setNomeCorso(corsoDTO.getNomeCorso());
        corso.setOreCorso(corsoDTO.getOreCorso());
        corso.setAnnoAccademico(corsoDTO.getAnnoAccademico());
        if(corsoDTO.getDocente() != null){
            corso.setDocente(docenteConverter.fromDtoToEntity(corsoDTO.getDocente()));
        }else{
            corso.setDocente(null);
        }

        return corso;
    }
}

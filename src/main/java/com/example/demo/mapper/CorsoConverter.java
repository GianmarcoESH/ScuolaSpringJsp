package com.example.demo.mapper;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

        if(corso.getDiscenteList() != null){
            List<Long> discentiIdsList = corso.getDiscenteList().stream().map(Discente::getId).toList();
            corsoDTO.setDiscenteIds(discentiIdsList);
        }else{
            corsoDTO.setDiscenteIds(new ArrayList<>());
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


    public List<CorsoDTO> fromEntityListToDtoList(List<Corso> corsoList){
        return corsoList.stream()
                .map(this::fromEntityToDto).toList();
    }

}

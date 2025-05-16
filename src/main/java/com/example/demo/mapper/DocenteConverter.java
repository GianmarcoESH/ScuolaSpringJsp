package com.example.demo.mapper;


import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class DocenteConverter {



    public DocenteDTO fromEntityToDto (Docente docente) {
        DocenteDTO docenteDTO = new DocenteDTO();
        docenteDTO.setId(docente.getId());
        docenteDTO.setNome(docente.getNome());
        docenteDTO.setCognome(docente.getCognome());
        docenteDTO.setDataDiNascita(docente.getDataDiNascita());
        docenteDTO.setCorsi(corsoListToDTOList(docente.getCorsi()));


        return docenteDTO;
    }

    public Docente fromDtoToEntity(DocenteDTO dto){
        Docente docente = new Docente();
        docente.setId(dto.getId());
        docente.setNome(dto.getNome());
        docente.setCognome(dto.getCognome());
        docente.setDataDiNascita(dto.getDataDiNascita());

        return docente;
    }

    public  List<CorsoDTO> corsoListToDTOList(List<Corso> corsoList) {
        List<CorsoDTO> corsoDTOList = new ArrayList<>();

        for (Corso corso : corsoList) {
            CorsoDTO corsoDTO = new CorsoDTO();
            corsoDTO.setId(corso.getId());
            corsoDTO.setNomeCorso(corso.getNomeCorso());
            corsoDTO.setOreCorso(corso.getOreCorso());
            corsoDTO.setAnnoAccademico(corso.getAnnoAccademico());
            corsoDTOList.add(corsoDTO);
        }
        return corsoDTOList;
    }

}

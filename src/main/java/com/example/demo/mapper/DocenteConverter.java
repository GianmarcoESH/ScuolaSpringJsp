package com.example.demo.mapper;


import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
import org.springframework.stereotype.Component;


@Component
public class DocenteConverter {

    public DocenteDTO fromEntityToDto (Docente docente) {
        DocenteDTO docenteDTO = new DocenteDTO();
        docenteDTO.setId(docente.getId());
        docenteDTO.setNome(docente.getNome());
        docenteDTO.setCognome(docente.getCognome());
        docenteDTO.setDataDiNascita(docente.getDataDiNascita());

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


}

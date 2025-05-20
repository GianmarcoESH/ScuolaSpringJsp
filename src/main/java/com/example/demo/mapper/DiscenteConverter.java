package com.example.demo.mapper;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.repository.CorsoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class DiscenteConverter {


    private final CorsoRepository corsoRepository;
    @Autowired
    public DiscenteConverter(CorsoRepository corsoRepository) {
        this.corsoRepository = corsoRepository;
    }


    public Discente fromDtoToEntity(DiscenteDTO discenteDTO){
        Discente discente = new Discente();
        discente.setId(discenteDTO.getId());
        discente.setNome(discenteDTO.getNome());
        discente.setCognome(discenteDTO.getCognome());
        discente.setCittaDiResidenza(discenteDTO.getCittaDiResidenza());
        discente.setDataDiNascita(discenteDTO.getDataDiNascita());
        discente.setVoto(discenteDTO.getVoto());
        //mappare corsi
        List<Corso> corsoList = new ArrayList<>();
        for(Long ids : discenteDTO.getCorsiIds()){
            Corso corso = corsoRepository.findById(ids).orElseThrow(()
                    -> new RuntimeException("Corso non trovato"));
            corsoList.add(corso);


        }
        discente.setCorsi(corsoList);


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


    public List<CorsoDTO> convertListToDTOList(List<Corso> corsi){

        if(corsi == null || corsi.isEmpty()){
            System.out.println("Lista vuota o nulla");
        }else{
            System.out.println("Lista: " + corsi.size());
        }

        List<CorsoDTO> corsoDTOList = new ArrayList<>();

        for (Corso c :corsi){
            CorsoDTO corsoDTO = new CorsoDTO();
            corsoDTO.setId(c.getId());
            corsoDTO.setNomeCorso(c.getNomeCorso());
            corsoDTO.setAnnoAccademico(c.getAnnoAccademico());
            corsoDTO.setOreCorso(c.getOreCorso());

            corsoDTOList.add(corsoDTO);
        }



        return corsoDTOList;
    }

}

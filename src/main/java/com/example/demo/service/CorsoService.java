package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.mapper.CorsoConverter;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CorsoService {
    private final CorsoRepository corsoRepository;

    @Autowired
    private DocenteService docenteService;
    @Autowired
    CorsoConverter corsoConverter;
    @Autowired
    private DiscenteRepository discenteRepository;
    @Autowired
    private DiscenteService discenteService;

    @Autowired
    public CorsoService(CorsoRepository corsoRepository){
        this.corsoRepository = corsoRepository;
    }
    public List<CorsoDTO> findAll(){
        return corsoRepository.findAllByOrderByIdAsc()
                .stream()
                .map(corso -> corsoConverter.fromEntityToDto(corso))
                .toList();
    }
    /*
    public List<CorsoDTO> findAll() {
        return corsoRepository.findAll(Sort.by("id")).stream()
                .map(corso -> corsoConverter.fromEntityToDto(corso))
                .toList();
    }*/

    public CorsoDTO get(Long id) {
        Corso corso = corsoRepository.findById(id).orElseThrow();
        return corsoConverter.fromEntityToDto(corso);
    }

    public Corso save(CorsoDTO c) {
        Corso corso = corsoConverter.fromDtoToEntity(c);
        if(c.getDocente() != null && c.getDocente().getId() != null){
           corso.setDocente(docenteService.getDocente(c.getDocente().getId()));
        }else{
            corso.setDocente(null);
        }
        return corsoRepository.save(corso);

    }


    public Corso update(Long id, CorsoDTO c) {
        Corso corso = corsoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Corso non trovato"));

        corso.setNomeCorso(c.getNomeCorso());
        corso.setAnnoAccademico(c.getAnnoAccademico());
        corso.setOreCorso(c.getOreCorso());

        if(c.getDocente() != null && c.getDocente().getId() != null){
            corso.setDocente(docenteService.getDocente(c.getDocente().getId()));
        }else{
            corso.setDocente(null);
        }

        if(c.getDiscenteIds() != null){
            List<Discente> discentiList = c.getDiscenteIds().stream()
                    .map(idDiscente -> discenteService.getDiscenteById(idDiscente))
                    .collect(Collectors.toList());

            corso.setDiscenteList(discentiList);
        }else{
            corso.setDiscenteList(new ArrayList<>());
        }

        return corsoRepository.save(corso);
    }

    public void delete(Long id) {corsoRepository.deleteById(id);}


}

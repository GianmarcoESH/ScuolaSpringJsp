package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.entity.Corso;
import com.example.demo.mapper.CorsoConverter;
import com.example.demo.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class CorsoService {
    private final CorsoRepository corsoRepository;

    @Autowired
    private DocenteService docenteService;
    @Autowired
    CorsoConverter corsoConverter;

    @Autowired
    public CorsoService(CorsoRepository corsoRepository){
        this.corsoRepository = corsoRepository;
    }
    public List<CorsoDTO> find(){
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
           c.setDocente(docenteService.get(c.getDocente().getId()));
        }else{
            corso.setDocente(null);
        }
        return corsoRepository.save(corso);

    }

    public void delete(Long id) {corsoRepository.deleteById(id);}


}

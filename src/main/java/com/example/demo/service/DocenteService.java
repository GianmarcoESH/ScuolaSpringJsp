package com.example.demo.service;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
import com.example.demo.mapper.DocenteConverter;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocenteService {


    @Autowired
    DocenteRepository docenteRepository;

    @Autowired
    DocenteConverter docenteConverter;

    public List<DocenteDTO> findAll() {

        return docenteRepository.findAll()
                .stream()
                .map(docente -> docenteConverter.fromEntityToDto(docente))
                .collect(Collectors.toList());
    }

    public DocenteDTO get(Long id) {

        return docenteConverter.fromEntityToDto(docenteRepository.findById(id).orElseThrow());
    }

    public Docente save(DocenteDTO d) {
        Docente doc = docenteConverter.fromDtoToEntity(d);
        return docenteRepository.save(doc);
    }

    public void delete(Long id) {
        docenteRepository.deleteById(id);
    }


}

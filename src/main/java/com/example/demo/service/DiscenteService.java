package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.mapper.DiscenteConverter;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DiscenteService {
    @Autowired
    DiscenteRepository discenteRepository;

    @Autowired
    DiscenteConverter discenteConverter;

    public List<DiscenteDTO> findAll() {
        return discenteRepository.findAll().stream()
                .map(discente -> discenteConverter.fromEntityToDto(discente))
                .toList();
    }

    public DiscenteDTO get(Long id) {
        Discente discente = discenteRepository.findById(id).orElseThrow();
        return discenteConverter.fromEntityToDto(discente);
    }

    public Discente save(DiscenteDTO discenteDTO) {
        return discenteRepository.save(discenteConverter.fromDtoToEntity(discenteDTO));
    }

    public void delete(Long id) {discenteRepository.deleteById(id);}

    public List<DiscenteDTO> findByNameOrLastname(String keyword){
        List<Discente> discenteList = discenteRepository.findByNameorLastName(keyword);
        List<DiscenteDTO> discenteDTOList = new ArrayList<>();
        for (Discente d : discenteList) {
            discenteDTOList.add(discenteConverter.fromEntityToDto(d));
        }

        return discenteDTOList;

    }

    public List<DiscenteDTO> findPassedStudent() {

        List<Discente> discenteList =discenteRepository.findPassedStudent();
        List<DiscenteDTO> discenteDTOList = new ArrayList<>();
        for (Discente d : discenteList) {
            discenteDTOList.add(discenteConverter.fromEntityToDto(d));
        }

        return discenteDTOList;
    }

    public List<DiscenteDTO> findByCity(String citta) {
        List<Discente> discenteList = discenteRepository.findByCity(citta);
        List<DiscenteDTO> discenteDTOList = new ArrayList<>();
        for (Discente d : discenteList) {
            discenteDTOList.add(discenteConverter.fromEntityToDto(d));
        }

        return discenteDTOList;
    }


    public List<CorsoDTO> findCorsiByDiscenteId(Long discenteId) {

        Discente discente = discenteRepository.findById(discenteId)
                .orElseThrow(()-> new RuntimeException("Discente non trovato"));


        return discenteConverter.convertListToDTOList(discente.getCorsi());
    }



}

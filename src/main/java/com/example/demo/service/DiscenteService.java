package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.mapper.DiscenteConverter;
import com.example.demo.repository.CorsoRepository;
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

    @Autowired
    CorsoRepository corsoRepository;

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
        Discente discente = discenteConverter.fromDtoToEntity(discenteDTO);

        List<Corso> corsoList = new ArrayList<>();
        for (Long corsoId : discenteDTO.getCorsiIds()) {
            Corso corso = corsoRepository.findById(corsoId)
                    .orElseThrow(() -> new RuntimeException("Corso non trovato"));

            if (!corso.getDiscenteList().contains(discente)) {
                corso.getDiscenteList().add(discente);
            }

            corsoList.add(corso);
        }

        // Imposta i corsi nel discente
        discente.setCorsi(corsoList);

        // Salva il discente (salverà anche la relazione nella tabella di join discente_corso)
        Discente savedDiscente = discenteRepository.save(discente);

        // Se necessario, salva i corsi modificati (per aggiornare la relazione)
        for (Corso corso : corsoList) {
            corsoRepository.save(corso);  // Salva i corsi aggiornati
        }

        return savedDiscente;
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

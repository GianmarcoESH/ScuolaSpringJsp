package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.mapper.CorsoConverter;
import com.example.demo.mapper.DiscenteConverter;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DiscenteService {
    @Autowired
    DiscenteRepository discenteRepository;

    @Autowired
    DiscenteConverter discenteConverter;

    @Autowired
    CorsoRepository corsoRepository;
    @Autowired
    private CorsoConverter corsoConverter;

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


        discente.setCorsi(corsoList);

        discenteRepository.save(discente);


        return discente;
    }


    public Discente update(Long id, DiscenteDTO discenteDTO,
                           List<Long> corsiIdsDaAggiungere,List<Long> corsiIdsDaRimuovere) {
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discente non trovato"));

        discente.setNome(discenteDTO.getNome());
        discente.setCognome(discenteDTO.getCognome());
        discente.setVoto(discenteDTO.getVoto());
        discente.setDataDiNascita(discenteDTO.getDataDiNascita());
        discente.setCittaDiResidenza(discenteDTO.getCittaDiResidenza());

        //aggiunta dei corsi
        if(corsiIdsDaAggiungere != null) {
            for (Long corsoId : corsiIdsDaAggiungere) {
                Corso corso = corsoRepository.findById(corsoId)
                        .orElseThrow(() -> new EntityNotFoundException("Corso non trovato"));

                if(!discente.getCorsi().contains(corso)){
                    discente.getCorsi().add(corso);
                    corso.getDiscenteList().add(discente);
                }

            }
        }

        //rimozione corsi
        if (corsiIdsDaRimuovere != null) {
            for (Long corsoId : corsiIdsDaRimuovere) {
                Corso corso = corsoRepository.findById(corsoId)
                        .orElseThrow(() -> new EntityNotFoundException("Corso non trovato"));
                discente.getCorsi().remove(corso);
                corso.getDiscenteList().remove(discente);
            }
        }

        return discenteRepository.save(discente);


    }


    public void delete(Long id) {
        Discente discente = discenteRepository.findById(id).orElseThrow();
        if (!discente.getCorsi().isEmpty()) {
            discente.getCorsi().forEach(corso -> corso.getDiscenteList().remove(discente));
        }
        discenteRepository.deleteById(id);
    }

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


    public List<CorsoDTO> getCorsiNonAssociati(Long id){
        Discente discente= discenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discente non trovato"));

        List<Corso> corsiIscritti = discente.getCorsi();
        List<Corso> tuttiICorsi = corsoRepository.findAll();

        List<Corso> corsiNonAssociati = tuttiICorsi.stream()
                .filter(c -> !corsiIscritti.contains(c))
                .toList();

        return corsoConverter.fromEntityListToDtoList(corsiNonAssociati);
    }

    public Discente getDiscenteById(Long id){
        return discenteRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Discente non trovato"));
    }


}

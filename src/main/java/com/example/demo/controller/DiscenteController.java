package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DiscenteService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/discenti")
public class DiscenteController {

    private final DiscenteService discenteService;
    private final CorsoService corsoService;

    @Autowired
    public DiscenteController(DiscenteService discenteService, CorsoService corsoService) {
        this.discenteService = discenteService;
        this.corsoService = corsoService;
    }


    @GetMapping("/lista")
    public ModelAndView list(@RequestParam(name = "keyword",required = false) String keyword,
                             @RequestParam(name = "citta", required = false) String citta) {
        ModelAndView modelAndView = new ModelAndView();
        List<DiscenteDTO> discenti;
        if(keyword != null){
            discenti = discenteService.findByNameOrLastname(keyword);

        }else if(citta != null){
            discenti = discenteService.findByCity(citta);
            modelAndView.addObject("filterType", "citta");
            modelAndView.addObject("citta", citta);
        } else {
            discenti = discenteService.findAll();
        }
        modelAndView.setViewName("list-discente");
        modelAndView.addObject("discenti", discenti);
        modelAndView.addObject("filterType", "all");
        return modelAndView;
    }

    @GetMapping("/promossi")
    public ModelAndView listaPromossi(){
        ModelAndView modelAndView = new ModelAndView();
        List<DiscenteDTO> discenti = discenteService.findPassedStudent();
        modelAndView.setViewName("list-discente");
        modelAndView.addObject("discenti", discenti);
        modelAndView.addObject("filterType", "promossi");
        return modelAndView;
    }

    @GetMapping("/nuovo")
    public ModelAndView showAdd(){
        ModelAndView modelAndView = new ModelAndView("form-discente");
        DiscenteDTO discente = new DiscenteDTO();
        List<CorsoDTO> corsoDTOList = corsoService.findAll();
        modelAndView.addObject("discente", discente);
        modelAndView.addObject("corsoDTOList", corsoDTOList);
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView create(@ModelAttribute("discente") DiscenteDTO discenteDTO, BindingResult br) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(discenteDTO);
        if(br.hasErrors()){
            modelAndView.setViewName("form-discente");
            return modelAndView;
        }
        discenteService.save(discenteDTO);
        modelAndView.setViewName("redirect:/discenti/lista");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("update-discente");
        DiscenteDTO discenteDTO = discenteService.get(id);
        List<CorsoDTO> corsiAssociati = discenteService.findCorsiByDiscenteId(id);
        List<CorsoDTO> corsiNonAssociati = discenteService.getCorsiNonAssociati(id);
        modelAndView.addObject("discente", discenteDTO);
        modelAndView.addObject("corsiAssociati", corsiAssociati);
        modelAndView.addObject("corsiNonAssociati", corsiNonAssociati);
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @ModelAttribute("discente") DiscenteDTO discenteDTO, BindingResult br,
                               @RequestParam(required = false) List<Long> corsiIdsDaAggiungere,
                               @RequestParam(required = false) List<Long> corsiIdsDaRimuovere ) {
        ModelAndView modelAndView = new ModelAndView();
        if(br.hasErrors()){
            modelAndView.setViewName("form-discente");
            return modelAndView;
        }
        if (corsiIdsDaAggiungere == null) corsiIdsDaAggiungere = List.of();
        if (corsiIdsDaRimuovere == null) corsiIdsDaRimuovere = List.of();
        /*
        discenteDTO.setId(id);
        discenteService.save(discenteDTO);*/
        discenteService.update(id,discenteDTO,corsiIdsDaAggiungere,corsiIdsDaRimuovere);
        modelAndView.setViewName("redirect:/discenti/lista");
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        discenteService.delete(id);
        modelAndView.setViewName("redirect:/discenti/lista");
        return modelAndView;
    }


    @GetMapping("/{id}/corsi")
    public ModelAndView getCorsiPerDiscente(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("corsi-discente");

        DiscenteDTO discente = discenteService.get(id);
        List<CorsoDTO> corsi = discenteService.findCorsiByDiscenteId(discente.getId());

        modelAndView.addObject("discente", discente);
        modelAndView.addObject("corsi", corsi);


        return modelAndView;
    }
}

package com.example.demo.controller;

import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Discente;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/discenti")
public class DiscenteController {

    @Autowired
    DiscenteService DiscenteService;

    @GetMapping("/lista")
    public ModelAndView list(@RequestParam(name = "keyword",required = false) String keyword,
                             @RequestParam(name = "citta", required = false) String citta) {
        ModelAndView modelAndView = new ModelAndView();
        List<DiscenteDTO> discenti;
        if(keyword != null){
            discenti = DiscenteService.findByNameOrLastname(keyword);

        }else if(citta != null){
            discenti = DiscenteService.findByCity(citta);
            modelAndView.addObject("filterType", "citta");
            modelAndView.addObject("citta", citta);
        } else {
            discenti = DiscenteService.findAll();
        }
        modelAndView.setViewName("list-discente");
        modelAndView.addObject("discenti", discenti);
        modelAndView.addObject("filterType", "all");
        return modelAndView;
    }

    @GetMapping("/promossi")
    public ModelAndView listaPromossi(){
        ModelAndView modelAndView = new ModelAndView();
        List<DiscenteDTO> discenti = DiscenteService.findPassedStudent();
        modelAndView.setViewName("list-discente");
        modelAndView.addObject("discenti", discenti);
        modelAndView.addObject("filterType", "promossi");
        return modelAndView;
    }

    @GetMapping("/nuovo")
    public ModelAndView showAdd(){
        ModelAndView modelAndView = new ModelAndView("form-discente");
        Discente discente = new Discente();
        modelAndView.addObject("discente", discente);
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView create(@ModelAttribute("discente") DiscenteDTO discenteDTO, BindingResult br) {
        ModelAndView modelAndView = new ModelAndView();
        if(br.hasErrors()){
            modelAndView.setViewName("form-discente");
            return modelAndView;
        }
        DiscenteService.save(discenteDTO);
        modelAndView.setViewName("redirect:/discenti/lista");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("form-discente");
        modelAndView.addObject("discente", DiscenteService.get(id));
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @ModelAttribute("discente") DiscenteDTO discenteDTO, BindingResult br) {
        ModelAndView modelAndView = new ModelAndView();
        if(br.hasErrors()){
            modelAndView.setViewName("form-discente");
            return modelAndView;
        }
        discenteDTO.setId(id);
        DiscenteService.save(discenteDTO);
        modelAndView.setViewName("redirect:/discenti");
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        DiscenteService.delete(id);
        modelAndView.setViewName("redirect:/discenti/lista");
        return modelAndView;
    }
}

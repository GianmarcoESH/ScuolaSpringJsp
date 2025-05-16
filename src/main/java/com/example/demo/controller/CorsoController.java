package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/corsi")
public class CorsoController {
    @Autowired
    private CorsoService corsoService;

    @Autowired
    private DocenteService docenteService;



    @GetMapping("/lista")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        List<CorsoDTO> corsi = corsoService.findAll();
        modelAndView.setViewName("list-corsi");
        modelAndView.addObject("corsi", corsi);

        return modelAndView;
    }

    @GetMapping("/nuovo")
    public ModelAndView showAdd(){
        ModelAndView modelAndView = new ModelAndView("form-corso");
        Corso corso = new Corso();
        List<DocenteDTO> docenteList= docenteService.findAll();
        modelAndView.addObject("docenti",docenteList);
        modelAndView.addObject("corso", corso);
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView create(@ModelAttribute("corso") CorsoDTO corsoDTO, BindingResult br) {
        ModelAndView modelAndView = new ModelAndView();
        if(br.hasErrors()){
            modelAndView.setViewName("form-corso");
            return modelAndView;
        }
        corsoService.save(corsoDTO);
        modelAndView.setViewName("redirect:/corsi/lista");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("form-corso");
        List<DocenteDTO> docenteList= docenteService.findAll();
        modelAndView.addObject("docenti",docenteList);
        modelAndView.addObject("corso", corsoService.get(id));
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @ModelAttribute("corso") CorsoDTO corsoDTO, BindingResult br){
        ModelAndView modelAndView = new ModelAndView();
        if(br.hasErrors()){
            modelAndView.setViewName("form-corso");
            return modelAndView;
        }
        corsoDTO.setId(id);
        corsoService.save(corsoDTO);
        modelAndView.setViewName("redirect:/corsi");
        return modelAndView;

    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView();
        corsoService.delete(id);
        modelAndView.setViewName("redirect:/corsi/lista");
        return modelAndView;
    }
}

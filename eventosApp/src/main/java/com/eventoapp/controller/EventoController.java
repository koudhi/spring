package com.eventoapp.controller;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;
import com.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository er;
    @Autowired
    private ConvidadoRepository cr;

    @GetMapping("/cadastrarEvento")
    public String form(){
        return "evento/formEvento";
    }


    @PostMapping("/cadastrarEvento")
    public String form(@Valid @ModelAttribute Evento evento, BindingResult result, RedirectAttributes atributes){
        if(result.hasErrors()){
            atributes.addFlashAttribute("mensagem","Verifique os campos!!");
            return "redirect:/{codigo}";
        }
        er.save(evento);
        atributes.addFlashAttribute("mensagem","Convidado adicionado com sucesso!!");
        return "redirect:/eventos";
    }

    @RequestMapping("/eventos")
    public ModelAndView listEventos(){
        ModelAndView mv = new ModelAndView("evento/listEvento");
        Iterable<Evento> eventos= er.findAll();
        mv.addObject("eventos",eventos);

        return mv;
    }

    @RequestMapping("/deletar")
    public String deletarEvento(long codigo){
        Evento evento = er.findByCodigo(codigo);
        er.delete(evento);
        return "redirect:/eventos";
    }

    @GetMapping(value = "/{codigo}")
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo){
        Evento evento = er.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("evento/detalhesEvento");
        mv.addObject("evento",evento);
        Iterable<Convidado> convidados = cr.findByEvento(evento);
        mv.addObject("convidados",convidados);
        return mv;
    }


    @PostMapping( "/{codigo}")
    public String addConvidado(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes atributes){
        if(result.hasErrors()){
            atributes.addFlashAttribute("mensagem","Verifique os campos!!");
            return "redirect:/{codigo}";
        }
        Evento evento = er.findByCodigo(codigo);
        convidado.setEvento(evento);
        cr.save(convidado);
        atributes.addFlashAttribute("mensagem","Convidado adicionado com sucesso!!");
        return "redirect:/{codigo}";
    }

    @RequestMapping("/deletarConvidado")
    public String deletarConvidado(String rg){
        Convidado convidado = cr.findByRg(rg);
        Evento evento = convidado.getEvento();
        cr.delete(convidado);
        long codigo = evento.getCodigo();
        String redirectUrl = "redirect:/"+codigo;
        return redirectUrl;
    }
}

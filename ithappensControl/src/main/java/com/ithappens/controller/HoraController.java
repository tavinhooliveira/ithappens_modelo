package com.ithappens.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ithappens.model.Hora;
import com.ithappens.model.Responsavel;
import com.ithappens.model.Task;
import com.ithappens.repository.Horas;
import com.ithappens.repository.Responsaveis;

@Controller
@RequestMapping("/ithappens/horas")
public class HoraController {

	private static final String LIST_HORAS_VIEW = "/pages/ListarHoras";
	private static final String ALTER_HORAS_VIEW = "/pages/AlterarHoras";
	private static final String LIST_HORAS_RESPONSAVEL_VIEW = "/pages/ListarHorasResponsavel";

	@Autowired
	private Horas horas;

	@Autowired
	private Responsaveis responsaveis;

	// Salvar
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Hora hora, Task task, Errors errors, RedirectAttributes attributes) {
		attributes.addAttribute(task);
		if (errors.hasErrors()) {
			return "redirect:/ithappens/detalhes/" + hora.getTasks().getCodigo();
		}
		try {
			horas.save(hora);
			attributes.addFlashAttribute("mensagem", "Hora adicionada com sucesso!");
			return "redirect:/ithappens/detalhes/" + hora.getTasks().getCodigo();
		} catch (IllegalArgumentException e) {
			errors.rejectValue(null, e.getMessage());
			return "redirect:/ithappens/detalhes/" + hora.getTasks().getCodigo();
		}
	}

	// list
	@RequestMapping
	public ModelAndView lista() {
		List<Hora> allHoras = horas.findAll();
		ModelAndView mv = new ModelAndView(LIST_HORAS_VIEW);
		mv.addObject(new Hora());
		mv.addObject("horas", allHoras);
		return mv;
	}
	

	// LitasTodasAsHorasDeUmResponsavel
	@RequestMapping(value = "/responsavel/list/{codigo}")
	public ModelAndView listaResposavelHoras(@PathVariable("codigo") Responsavel responsavel) {
		ModelAndView mv = new ModelAndView(LIST_HORAS_RESPONSAVEL_VIEW);
		mv.addObject(responsavel);
		List<Hora> allHoras = horas.findAll();
		mv.addObject("horas", allHoras);
		return mv;
	}
	
	
	
	// Editar
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Hora hora) {
		ModelAndView mv = new ModelAndView(ALTER_HORAS_VIEW);
		mv.addObject(hora);
		List<Responsavel> allResponsavel = responsaveis.findAll();
		mv.addObject(new Responsavel());
		mv.addObject("tdresponsaveis", allResponsavel);
		return mv;
	}

	// Excluir
	@RequestMapping(value = "/delete/{codigo}")
	public String excluir(@PathVariable("codigo") Hora hora) {
		horas.delete(hora);
		return "redirect:/ithappens/detalhes/" + hora.getTasks().getCodigo();
	}

}

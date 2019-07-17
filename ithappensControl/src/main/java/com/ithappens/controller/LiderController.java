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

import com.ithappens.model.Lider;
import com.ithappens.model.Responsavel;
import com.ithappens.repository.Lideres;


@Controller
@RequestMapping("/ithappens/lider")
public class LiderController {
	
	private static final String CADASTRO_LIDER_VIEW = "/pages/AlterarLider";
	private static final String LIDER_VIEW = "/pages/ListarLideres";
	
	@Autowired
	private Lideres	lideres;
	
	
	// Cadastro Novo
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_LIDER_VIEW);
		mv.addObject(new Lider());
		return mv;
	}
	
	// list
	@RequestMapping
	public ModelAndView lista() {
		List<Lider> allLideres = lideres.findAll();
		ModelAndView mv = new ModelAndView(LIDER_VIEW);
		mv.addObject(new Responsavel());
		mv.addObject("lideres", allLideres);
		return mv;
	}
	
	// Salvar 
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Lider lider, Errors errors,
			RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_LIDER_VIEW;
		}
		try {
			lideres.save(lider);
			attributes.addFlashAttribute("mensagem",
					"Lider Salvo com sucesso!");
			return "redirect:/ithappens/novo";
		} catch (IllegalArgumentException e) {
			return CADASTRO_LIDER_VIEW;
		}
	}
	
	
	
	// Editar
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Lider lider) {
		ModelAndView mv = new ModelAndView(CADASTRO_LIDER_VIEW);
		mv.addObject(lider);
		return mv;
	}
	
	
	// Excluir
	@RequestMapping(value = "/delete/{codigo}")
	public String excluir(@PathVariable Long codigo,
			@Validated Lider lider, Errors errors,
			RedirectAttributes attributes) {
		lideres.delete(codigo);
		attributes.addFlashAttribute("mensagem",
				"Lider excluída com sucesso!");
		return "redirect:/ithappens/lider";
	}
	

}

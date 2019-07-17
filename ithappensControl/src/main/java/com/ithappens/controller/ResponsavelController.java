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

import com.ithappens.model.Responsavel;
import com.ithappens.model.Task;
import com.ithappens.repository.Responsaveis;

@Controller
@RequestMapping("/ithappens/responsavel")
public class ResponsavelController {

	private static final String CADASTRO_RESPONSAVEL_VIEW = "/pages/AlterarResponsavel";
	private static final String RESPONSAVEL_VIEW = "/pages/ListarResponsavel";

	@Autowired
	private Responsaveis responsaveis;

	// Cadastro Novo
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_RESPONSAVEL_VIEW);
		mv.addObject(new Responsavel());
		return mv;
	}

	// list
	@RequestMapping
	public ModelAndView lista() {
		List<Responsavel> allResponsaveis = responsaveis.findAll();
		ModelAndView mv = new ModelAndView(RESPONSAVEL_VIEW);
		mv.addObject(new Responsavel());
		mv.addObject("responsaveis", allResponsaveis);
		return mv;
	}

	// Salvar Responsavel
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Responsavel responsavel, Task task, Errors errors,
			RedirectAttributes attributes) {
		attributes.addAttribute(task);
		if (errors.hasErrors()) {
			return CADASTRO_RESPONSAVEL_VIEW;
		}
		try {
			responsaveis.save(responsavel);
			attributes.addFlashAttribute("mensagem","Responsavel Salvo com sucesso!");
			return "redirect:/ithappens/novo";
		} catch (IllegalArgumentException e) {
			return CADASTRO_RESPONSAVEL_VIEW;
		}
	}
	
	
	// Editar
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Responsavel responsavel) {
		ModelAndView mv = new ModelAndView(CADASTRO_RESPONSAVEL_VIEW);
		mv.addObject(responsavel);
		return mv;
	}
	
	
	// Excluir
	@RequestMapping(value = "/delete/{codigo}")
	public String excluir(@PathVariable Long codigo,
			@Validated Responsavel responsavel, Errors errors,
			RedirectAttributes attributes) {
		responsaveis.delete(codigo);
		attributes.addFlashAttribute("mensagem",
				"Responsavel excluída com sucesso!");
		return "redirect:/ithappens/responsavel";
	}

}

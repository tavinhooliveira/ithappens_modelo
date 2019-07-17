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

import com.ithappens.model.Modulo;
import com.ithappens.repository.Modulos;

@Controller
@RequestMapping("/ithappens/modulo")
public class ModulosController {
	
	private static final String CADASTRO_MODULO_VIEW = "/pages/AlterarModulos";
	private static final String MODULO_VIEW = "/pages/ListarModulos";

	
	@Autowired
	public Modulos modulos;
	
	
	
	// Cadastro Novo
		@RequestMapping("/novo")
		public ModelAndView novo() {
			ModelAndView mv = new ModelAndView(CADASTRO_MODULO_VIEW);
			mv.addObject(new Modulo());
			return mv;
		}
		
		// list
		@RequestMapping
		public ModelAndView lista() {
			List<Modulo> allModulos = modulos.findAll();
			ModelAndView mv = new ModelAndView(MODULO_VIEW);
			mv.addObject(new Modulo());
			mv.addObject("modulos", allModulos);
			return mv;
		}
		
		
		// Salvar
		@RequestMapping(method = RequestMethod.POST)
		public String salvar(@Validated Modulo modulo, Errors errors,
				RedirectAttributes attributes) {
			if (errors.hasErrors()) {
				return CADASTRO_MODULO_VIEW;
			}
			try {
				modulos.save(modulo);
				attributes.addFlashAttribute("mensagem","Modulo Salvo com sucesso!");
				return "redirect:/ithappens/novo";
			} catch (IllegalArgumentException e) {
				return CADASTRO_MODULO_VIEW;
			}
		}
		
				
		
		// Editar
		@RequestMapping("{codigo}")
		public ModelAndView edicao(@PathVariable("codigo") Modulo modulo) {
			ModelAndView mv = new ModelAndView(CADASTRO_MODULO_VIEW);
			mv.addObject(modulo);
			return mv;
		}
		
		
		// Excluir
		@RequestMapping(value = "/delete/{codigo}")
		public String excluir(@PathVariable Long codigo,
				@Validated Modulo modulo, Errors errors,
				RedirectAttributes attributes) {
			modulos.delete(codigo);
			attributes.addFlashAttribute("mensagem","Modulo excluído com sucesso!");
			return "redirect:/ithappens/modulo";
		}
	
	

}

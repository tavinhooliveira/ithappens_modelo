package com.ithappens.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ithappens.model.Anexo;
import com.ithappens.model.Hora;
import com.ithappens.model.Client;
import com.ithappens.model.Branch;
import com.ithappens.model.User;
import com.ithappens.model.StatusTask;
import com.ithappens.model.Task;
import com.ithappens.model.TipoTask;
import com.ithappens.repository.Anexos;
import com.ithappens.repository.Horas;
import com.ithappens.repository.Clients;
import com.ithappens.repository.Branchs;
import com.ithappens.repository.Users;
import com.ithappens.repository.filter.TaskFilter;
import com.ithappens.service.CadastroTaskService;

@Controller
@RequestMapping("/ithappens")
public class TaskController {

	private static final String CADASTRO_VIEW = "/pages/CadastroTask";
	private static final String LIST_TASK_VIEW = "/pages/ListarTask";
	private static final String DETALHE_TASK_VIEW = "/pages/DetalheTask";

	@Autowired
	private CadastroTaskService cadastrotaskservice;

	@Autowired
	private Users users;

	@Autowired
	private Clients clients;

	@Autowired
	private Branchs branchs;

	@Autowired
	private Horas horas;
	
	@Autowired
	private Anexos anexos;

	
	// Cadastro Novo
	@RequestMapping("/novo")
	public ModelAndView novo(@ModelAttribute("filtro") TaskFilter filtro) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		List<Task> allTask = cadastrotaskservice.filtrar(filtro);
		mv.addObject("tasks", allTask);
		mv.addObject(new Task());
		return mv;
	}
	
	
	// Combo Users
	@ModelAttribute("tdusers")
	public List<User> tdusers() {
		return users.findAll();
	}
	
	// Combo Users
	@ModelAttribute("tdclients")
	public List<Client> tdclients() {
		return clients.findAll();
	}
	
	// Combo Branchs
	@ModelAttribute("tdbranchs")
	public List<Branch> tdbranchs() {
		return branchs.findAll();
	}
	
		
	// Salvar
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Task task, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;

		}
		try {
			cadastrotaskservice.salvar(task);
			attributes.addFlashAttribute("mensagem", "Task salva com sucesso!");
			return "redirect:/ithappens/detalhes/" + task.getCodigo().toString();
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	
	// Listar Task
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") TaskFilter filtro) {
		List<Task> allTask = cadastrotaskservice.filtrar(filtro);
		ModelAndView mv = new ModelAndView(LIST_TASK_VIEW);
		mv.addObject("tasks", allTask);
		return mv;
	}

	// PesquisaComboTipos
	@ModelAttribute("todasTasks")
	public List<TipoTask> todasTasks() {
		return Arrays.asList(TipoTask.values());
	}

	// PesquisaComboStauts
	@ModelAttribute("todasTasksStatus")
	public List<StatusTask> todasTasksStatus() {
		return Arrays.asList(StatusTask.values());
	}

	// Editar
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Task task) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(task);
		List<User> allUsers = users.findAll();
		mv.addObject("tdusers", allUsers);
		List<Client> allClients = clients.findAll();
		mv.addObject("tdclients", allClients);
		List<Branch> allBranchs = branchs.findAll();
		mv.addObject("tdbranchs", allBranchs);
		return mv;
	}

	// Detalhes Task
	@RequestMapping("detalhes/{codigo}")
	public ModelAndView exibir(@PathVariable("codigo") Task task) {
		ModelAndView mv = new ModelAndView(DETALHE_TASK_VIEW);
		mv.addObject(task);
		List<Hora> allHoras = horas.findAll();
		mv.addObject("horas", allHoras);
		List<User> allUsers = users.findAll();
		mv.addObject("tdusers", allUsers);
		List<Anexo> allAnexos = anexos.findAll();
		mv.addObject("anexos", allAnexos);

		return mv;
	}

	// Excluir
	@RequestMapping("delete/{codigo}")
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastrotaskservice.excluir(codigo);
		attributes.addFlashAttribute("mensagem", "Task excluída com sucesso!");
		return "redirect:/ithappens/";
	}

}

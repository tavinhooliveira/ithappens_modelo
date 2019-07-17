package com.ithappens.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ithappens.repository.Lideres;
import com.ithappens.repository.Modulos;
import com.ithappens.repository.Responsaveis;
import com.ithappens.repository.Tasks;


@Controller
@RequestMapping("/ithappens/dashboard")
public class DashboardController {	
	
	private static final String DASHBOARD = "/layout/Dashboard"; 
	
	@Autowired
	private Tasks tasks;
	
	@Autowired
	private Lideres liders;
	
	@Autowired
	private Responsaveis responsaveis;
	
	@Autowired
	private Modulos modulos;
	
	
	//Dashboard
	@RequestMapping
	public ModelAndView exibir() {
		ModelAndView mv = new ModelAndView(DASHBOARD);
		mv.addObject("taskContTotal", tasks.findByTaskTotalQTA());
		mv.addObject("taskContOpen", tasks.findByTaskOpenQTA());
		mv.addObject("taskContOnHold", tasks.findByTaskOnHoldQTA());
		mv.addObject("taskContClosed", tasks.findByTaskClosedQTA());
		mv.addObject("taskContRejected", tasks.findByTaskRejectedQTA());
		
		mv.addObject("pessoasContLideres", liders.findBypessoasContLideresQTA());
		mv.addObject("pessoasContResponsavel", responsaveis.findBypessoasContResponsavelQTA());
		
		mv.addObject("modulosAll", modulos.findBymodulosAllQTA());

		return mv;
	}
	
		
		
			


}

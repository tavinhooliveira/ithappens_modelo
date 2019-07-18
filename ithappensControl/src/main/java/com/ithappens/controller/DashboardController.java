package com.ithappens.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ithappens.repository.Clients;
import com.ithappens.repository.Branchs;
import com.ithappens.repository.Users;
import com.ithappens.repository.Tasks;


@Controller
@RequestMapping("/ithappens/dashboard")
public class DashboardController {	
	
	private static final String DASHBOARD = "/layout/Dashboard"; 
	
	@Autowired
	private Tasks tasks;
	
	@Autowired
	private Clients clients;
	
	@Autowired
	private Users users;
	
	@Autowired
	private Branchs branchs;
	
	
	//Dashboard
	@RequestMapping
	public ModelAndView exibir() {
		ModelAndView mv = new ModelAndView(DASHBOARD);
		mv.addObject("taskContTotal", tasks.findByTaskTotalQTA());
		mv.addObject("taskContOpen", tasks.findByTaskOpenQTA());
		mv.addObject("taskContOnHold", tasks.findByTaskOnHoldQTA());
		mv.addObject("taskContClosed", tasks.findByTaskClosedQTA());
		mv.addObject("taskContRejected", tasks.findByTaskRejectedQTA());
		
		mv.addObject("ContClients", clients.findByContClientsQTA());
		mv.addObject("ContUser", users.findByContUserQTA());
		
		mv.addObject("ContBranchs", branchs.findByContBranchsQTA());

		return mv;
	}
	
		
		
			


}

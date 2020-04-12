package com.nt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.nt.dto.EmployeeDTO;
import com.nt.service.EmployeeManagementService;

public class ListEmployeesController extends AbstractController
{

	private EmployeeManagementService ems;
	
	
	public ListEmployeesController(EmployeeManagementService ems) 
	{
		this.ems = ems;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		List<EmployeeDTO> listDTO = null;
		 listDTO = ems.fetchAllEmps();
		return new ModelAndView("show_emps", "listDTO", listDTO);
	}
	
}

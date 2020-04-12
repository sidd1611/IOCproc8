package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.nt.bo.EmployeeBO;
import com.nt.dao.EmployeeDAO;
import com.nt.dto.EmployeeDTO;

public class EmployeeManagementServiceImpl implements EmployeeManagementService
{

	private EmployeeDAO dao;
	
	public EmployeeManagementServiceImpl(EmployeeDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public List<EmployeeDTO> fetchAllEmps()
	{
		List<EmployeeBO> listBO = null;
		List<EmployeeDTO> listDTO = new ArrayList();
		//use DAO
		listBO = dao.getAllEmps();
		
//convert listBO to listDTO, because sending BO data directly to Controller is not a good practice
//also, we may need to make some changes to BO data or we may wish to add extra data or leave some data
		
		listBO.forEach((bo)->
		{
			EmployeeDTO dto = new EmployeeDTO();
			BeanUtils.copyProperties(bo, dto);//this method works only on Bean clases & when source 
			                         //bean has its all properties in target bean but not vice versa.
			                     //Also respective properties name and data-type must match
			dto.setsNo(listDTO.size()+1);
			listDTO.add(dto);
		});
		
		return listDTO;
	}//end of method
	
}//end of class

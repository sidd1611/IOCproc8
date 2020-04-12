//flow of program written below:  using which it will get Connection object from ConnectionPool
//using that

package com.nt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.nt.bo.EmployeeBO;
import com.nt.dto.EmployeeDTO;

public class EmployeeDAOImpl1 implements EmployeeDAO
{
	private static final String GET_ALL_EMPS = "SELECT EMPNO, ENAME, JOB, SAL, DEPTNO FROM EMP"; 
	private JdbcTemplate jt;
	
	public EmployeeDAOImpl1(JdbcTemplate jt) 
	{
		this.jt = jt;
	}

	@Override
	public List<EmployeeBO> getAllEmps()
	{
		List<EmployeeBO> listBO = null;
		listBO = jt.query(GET_ALL_EMPS, new ResultSetExtractor<List<EmployeeBO>>()
		{                                                  //Anonymous Inner Class implementing ResultSetExtractor interface
			@Override
			public List<EmployeeBO> extractData(ResultSet rs) throws SQLException, DataAccessException 
			{
				List<EmployeeBO> listBO = null;
				EmployeeBO bo = null;
				
				//copy ResultSet object records to BO class object and add them to List Collection
				listBO = new ArrayList();
				while(rs.next())
				{
					bo = new EmployeeBO();
					
					//copy each record to BO class object
					bo.setEmpNo(rs.getInt(1));
					bo.setEname(rs.getString(2));
					bo.setJob(rs.getString(3));
					bo.setSal(rs.getFloat(4));
					bo.setDeptNo(rs.getString(5));
					
					//add each BO class object to List Collection
					listBO.add(bo);
				}//end of while
				
				return listBO;
			}//end of extractData()
			
		}); //end of Anonymous Inner Class
		return listBO;
	}
	
}
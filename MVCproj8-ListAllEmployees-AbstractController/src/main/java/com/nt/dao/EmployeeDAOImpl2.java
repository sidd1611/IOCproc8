//flow of program written below:  using which it will get Connection object from ConnectionPool
//using that

package com.nt.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import com.nt.bo.EmployeeBO;

public class EmployeeDAOImpl2 implements EmployeeDAO
{
	private static final String GET_ALL_EMPS = "SELECT EMPNO, ENAME, JOB, SAL, DEPTNO FROM EMP"; 
	private JdbcTemplate jt;
	
	public EmployeeDAOImpl2(JdbcTemplate jt) 
	{
		this.jt = jt;
	}

	@Override
	public List<EmployeeBO> getAllEmps()
	{
		List<EmployeeBO> listBO1 = null;
		listBO1 = jt.query(GET_ALL_EMPS,  
				           rs->{                 //using Lambda Expression 
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
			}//end of Lambda Expression based Anonymous Inner Class
				           );//end of extractData() or LambdaExpression
		return listBO1;
	}
	
}
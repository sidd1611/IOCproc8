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

public class EmployeeDAOImpl implements EmployeeDAO
{
	
	private static final String GET_ALL_EMPS = "SELECT EMPNO, ENAME, JOB, SAL, DEPTNO FROM EMP";
	
	//JdbcTemplate will take care of common activities of JDBC Code like getting Connection object from Connection pool, creating statement object,
	//executing query, converting ResultSet object into different format of data, closing connections, handling exceptions 
	//etc. We need to perform only specific application-specific activities
	//So we can say that JdbcTemplate internally use plain jdbc and simplify jdbc style persistence logic development.  
	private JdbcTemplate jt;
	
	//As JdbcTemplate property is mandatory, so we will have a constructor for its initialization and instantiation through Constructor-Injection
	public EmployeeDAOImpl(JdbcTemplate jt) 
	{
		this.jt = jt;
	}

	@Override
	public List<EmployeeBO> getAllEmps()
	{
		List<EmployeeBO> listBO = null;
		//in line written below, query() method will execute 6 steps: (if query() method has one argument then it will use Statement object will be used
		                                                        // otherwise PreparedStatement object will be created & used
		listBO = jt.query(GET_ALL_EMPS, new EmployeeExtractor());//i)query() method will get the injected DataSource from jdbcTemplate.
		                                                         //ii)using this DataSource object, it will get a Connection object from Connection Pool
		                                                         //iii)using this Connection object, it will get a PreparedStatement (ps) object having "GET_All_EMPS" as pre-compiled query
		                                                         //iv)It will call executeQuery() method on ps and will get the ResultSet Object.
		                                                         //v)takes given ResultSetExtractor implementation class object as argument & call extractData() method by giving ResultSet object as an argument
		                                                         //vi)get Collection object from extractData() method
		//We use JdbcTemplate when we don't want to write plain jdbc code & we use callback method using JdbcTemplate object
		//when we don't want JdbcTemplate's method returned data & wish to make changes to the ResultSet data returned by it
		return listBO;
	}
	
	//Normal Inner class 
	private class EmployeeExtractor implements ResultSetExtractor<List<EmployeeBO>>//this interface is a Callback interface as 
	                                                                               //it contain a callback method
	{
		

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public List<EmployeeBO> extractData(ResultSet rs) throws SQLException, DataAccessException //extractData() is a type of
		            //callback method. Callback method is a method we need not to call as these type of methods will be called automatically
		           //when callback interface's implementation class's object is passed as an argument to method of JdbcTemplate
		           //called on JdbcTemplate object. This method thrwos exceptions as JdbcTemplate will handle it internally
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
		}//end of extractData() method
		
	}//end of inner class
	
}
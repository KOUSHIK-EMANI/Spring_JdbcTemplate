package com.reactiveworks.transaction.exp2;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class EmployeeDaoImpl implements EmployeeDao {
	private JdbcTemplate jdbcTemplate;
	private static final String INSERT = "INSERT INTO EMPLOYEE VALUES(?,?,?)";
	private static final String DELETE = "DELETE FROM EMPLOYEE WHERE EMPID = ?";
	private static final String UPDATE = "UPDATE EMPLOYEE SET NAME = ? WHERE EMPID = ?";
	private static final String SELECT_ALL = "SELECT * FROM EMPLOYEE";
	private static final String SELECT_COUNT = "SELECT COUNT(*) FROM EMPLOYEE";
	private static final String SELECT_SINGLE = "SELECT EMPID,NAME FROM EMPLOYEE WHERE EMPID = ?";
	private static final String SELECT_SINGLE_EMP = "SELECT * FROM EMPLOYEE WHERE EMPID = ?";

	public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	public int addEmployee(Employee employee) throws UnableToPerformTheOperation {
		int add=0;
		Object[] inputs = new Object[] { employee.getId(), employee.getName(), employee.getSalary() };
		try {
			jdbcTemplate.update(INSERT,new Object[] {109,"dsd",41645});
			jdbcTemplate.update(INSERT,new Object[] {110,"dsd",41645});
			jdbcTemplate.update(INSERT,new Object[] {105,"dsd",41645});
			
			
		} catch (DataAccessException e) {
			throw new UnableToPerformTheOperation("Unable to add employees", e);
		}
		return add;
	}

	public int deleteEmployee(Employee employee) throws UnableToPerformTheOperation {
		int update2;

		try {
			update2 = jdbcTemplate.update(DELETE, employee.getId());
		} catch (DataAccessException e) {
			throw new UnableToPerformTheOperation("Unable to delete employees", e);
		}
		return update2;
	}

	public int update(Employee employee) throws UnableToPerformTheOperation {
		Object[] inputs;
		int update2;
		try {
			inputs = new Object[] { employee.getName(), employee.getId() };
			update2 = jdbcTemplate.update(UPDATE, inputs);
		} catch (Exception e) {
			throw new UnableToPerformTheOperation("Unable to update employees", e);
		}
		return update2;

	}

	public List<Employee> getEmployee() throws UnableToFetchEmployeeDetails {
		List<Employee> queryList;
		try {
			queryList = jdbcTemplate.query(SELECT_ALL, new EmployeeMapper());
		} catch (DataAccessException e) {
			throw new UnableToFetchEmployeeDetails("unable to get the employee details", e);
		}
		return queryList;
	}

	public Integer getCountEmployee() throws UnableToFetchEmployeeDetails {
		Integer queryForObject;
		try {
			queryForObject = jdbcTemplate.queryForObject(SELECT_COUNT, Integer.class);
		} catch (DataAccessException e) {
			throw new UnableToFetchEmployeeDetails("unable to get the employee details", e);
		}
		return queryForObject;
	}

	public String getNameEmployee(int id) throws UnableToFetchEmployeeDetails {
		String queryForObject;
		try {
			queryForObject = jdbcTemplate.queryForObject(SELECT_SINGLE, new Object[] { id }, String.class);
		} catch (DataAccessException e) {
			throw new UnableToFetchEmployeeDetails("unable to get the employee details", e);
		}
		return queryForObject;
	}

	public Map<String, Object> getSingleEmployee(int id) throws UnableToFetchEmployeeDetails {
		Map<String, Object> query;
		try {
			query = jdbcTemplate.queryForMap(SELECT_SINGLE, new Object[] { id });
		} catch (DataAccessException e) {
			throw new UnableToFetchEmployeeDetails("unable to get the employee details", e);
		}
		return query;
	}

	public Employee getTotalSingleEmployee(int id) throws UnableToFetchEmployeeDetails {
		Employee query;
		try {
			query = jdbcTemplate.queryForObject(SELECT_SINGLE_EMP, new Object[] { id }, new EmployeeCustomMapper());
		} catch (DataAccessException e) {
			throw new UnableToFetchEmployeeDetails("unable to get the employee details", e);
		}
		return query;
	}


}

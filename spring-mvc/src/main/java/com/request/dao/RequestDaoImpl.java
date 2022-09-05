package com.request.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.request.model.Account;
import com.request.model.Customer;
import com.request.model.Request;
import com.util.Constant;
import com.util.MessageLogger;

@Component("requestDaoImpl")
public class RequestDaoImpl implements RequestDao {

	private static final Logger logger = LoggerFactory.getLogger(RequestDaoImpl.class);
	JdbcTemplate jdbcTemplate;

	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Properties getSqlProperties() {
		return sqlProperties;
	}

	public void setSqlProperties(Properties sqlProperties) {
		this.sqlProperties = sqlProperties;
	}

	@Override
	public Map<String, Object> getRequestDataByCustomerId(String customerId) throws DaoException {
		MessageLogger.debug(logger, String.format("getRequestDataByCustomerId(customerId = %s)", customerId));
		try {
			Map<String, Object> result = new HashMap<>();
			if (customerId == null || customerId.toString().trim().length() == 0) {
				MessageLogger.error(logger, "Customer id cannot be null or blank");
				throw new DaoException(true, "Customer id cannot be null or blank");
			}

			String chkRequestQuery = this.getSystemQuery("checkCustomer");

			int cnt = (int) this.jdbcTemplate.queryForObject(chkRequestQuery, new Object[] { customerId },
					Integer.class);
			if (cnt > 0) {
				String query = this.sqlProperties.getProperty("getRequestByCustomerId");
				Request req = this.jdbcTemplate.queryForObject(query, new Object[] { customerId },
						new BeanPropertyRowMapper<Request>(Request.class));
				result.put(Constant.CUSTOMER_ID, req.getCustomerId());
				result.put(Constant.PENSION_AMT, req.getPensionAmt());
				result.put(Constant.CONTRIBUTION_AMT, req.getMonthlyContribution());
				result.put(Constant.ACCOUNT_NO, req.getAccountNo());
				return result;
			}

			String customerInfoQuery = this.getSystemQuery("getCustomerInfo");
			Customer customer = jdbcTemplate.queryForObject(customerInfoQuery, new Object[] { customerId },
					new BeanPropertyRowMapper<Customer>(Customer.class));

			String accountQuery = this.getSystemQuery("getAccountsByCustomerId");

			List<Account> accounts = jdbcTemplate.query(accountQuery, new Object[] { customerId },
					new BeanPropertyRowMapper<>(Account.class));
			if (accounts.isEmpty()) {
				MessageLogger.error(logger, "No accounts found against customer id = " + customerId);
				throw new DaoException(true, "No accounts found against customer id = " + customerId);
			}

			String nomineeRelationshipQuery = this.getSystemQuery("getNomineeRelationship");
			List<Map<String, Object>> nomineeRelationship = jdbcTemplate.queryForList(nomineeRelationshipQuery);
			if (nomineeRelationship.isEmpty()) {
				MessageLogger.error(logger, "Could not fetch nominee relationship details");
				throw new DaoException(true, "Could not fetch nominee relationship");
			}

			result.put(Constant.NAME, customer.getFirstName());
			result.put(Constant.NOMINEE_RELATIONSHIP, nomineeRelationship);
			result.put(Constant.ACCOUNTS, accounts);
			return result;
		} catch (DaoException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			MessageLogger.error(logger, "Could not fetch request details" + ex);
			throw new DaoException(true, "Could not fetch request details");
		}
	}

	public String getSystemQuery(String key) throws DaoException {
		final String query = this.sqlProperties.getProperty(key);
		if (query == null) {
			throw new DaoException("[" + key + "] value is not set in sql.properties file!");
		}

		return query;
	}
}

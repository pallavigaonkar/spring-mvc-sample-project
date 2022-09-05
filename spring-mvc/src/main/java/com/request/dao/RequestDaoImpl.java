package com.request.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

			String chkRequestQuery = this.getSystemQuery("checkCustomerRequest");

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

	@Override
	public Map<String, String> saveRequest(Request request) throws DaoException {
		MessageLogger.debug(logger, String.format("saveRequest()"));
		Map<String, String> mapId = new HashMap<String, String>();
		try {
			String chkCustomerQuery = this.sqlProperties.getProperty("checkCustomerRequest");

			int cnt = this.jdbcTemplate.queryForObject(chkCustomerQuery, new Object[] { request.getCustomerId() },
					Integer.class);
			if (cnt == 0) {
				final String query = this.getSystemQuery("saveRequest");
				Connection connection = this.jdbcTemplate.getDataSource().getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(query,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, request.getBankName());
				preparedStatement.setString(2, request.getBranchName());
				preparedStatement.setString(3, request.getMicrCode());
				preparedStatement.setString(4, request.getIfscCode());
				preparedStatement.setString(5, request.getAccountNo());
				preparedStatement.setString(6, request.getCustomerId());
				preparedStatement.setString(7, request.getTitle());
				preparedStatement.setString(8, request.getName());
				preparedStatement.setString(9, request.getGender());
				preparedStatement.setString(10, request.getDob());
				preparedStatement.setString(11, request.getAddress());
				preparedStatement.setString(12, request.getMobileNo());
				preparedStatement.setString(13, request.getEmailId());
				preparedStatement.setString(14, request.getNomineeName());
				preparedStatement.setString(15, request.getNomineeAadharNo());
				preparedStatement.setString(16, request.getNomineeRelationship());
				preparedStatement.setString(17, request.getIsNomineeMinor());
				preparedStatement.setString(18, request.getGaurdianName());
				preparedStatement.setString(19, request.getContributionPeriod());
				preparedStatement.setString(20, request.getPensionAmt());
				preparedStatement.setString(21, request.getMonthlyContribution());
				preparedStatement.setString(22, request.getPanNumber());
				if (preparedStatement.executeUpdate() > 0) {
					ResultSet rset = preparedStatement.getGeneratedKeys();

					if (!rset.next()) {
						throw new DaoException("No id was returned for this new record!");
					}
					request.setId(rset.getInt(1));
				} else {
					throw new DaoException("No entry was made for this new record!");
				}
				MessageLogger.info(logger, request.getId() + " requestId...........");

				mapId.put(Constant.REQUEST_ID, Integer.toString(request.getId()));
			} else {
				throw new DaoException("Request is already registered for this customer");
			}
		} catch (DaoException ex) {
			throw ex;
		} catch (Exception ex) {
			MessageLogger.error(logger, "Could not save request details" + ex);
			throw new DaoException(true, "Could not save request details");
		}
		return mapId;
	}

	@Override
	public List<Request> getAllCustomerRequests() throws DaoException {
		MessageLogger.debug(logger, String.format("getAllCustomerRequests()"));
		try {
			String requestQuery = this.getSystemQuery("getRequests");
			return jdbcTemplate.query(requestQuery, new BeanPropertyRowMapper<>(Request.class));
		} catch (Exception ex) {
			MessageLogger.error(logger, "Could not fetch requests" + ex);
			throw new DaoException(true, "Could not fetch requests");
		}
	}
}

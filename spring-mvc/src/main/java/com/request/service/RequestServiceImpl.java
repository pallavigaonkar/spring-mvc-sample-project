package com.request.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.request.dao.RequestDao;
import com.request.model.Request;
import com.request.dao.DaoException;
import com.util.MessageLogger;

@Service
public class RequestServiceImpl implements IRequestService {
	private static final Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);

	@Autowired
	RequestDao reqDao;

	@Override
	public Map<String, Object> getRequestDataByCustomerId(String customerId) throws ServiceException {
		MessageLogger.debug(logger, String.format("getRequestDataByCustomerId(customerId = %s)", customerId));
		try {
			return reqDao.getRequestDataByCustomerId(customerId);
		} catch (DaoException ex) {
			MessageLogger.error(logger, "Error while retrieving request data", ex);
			throw new ServiceException(true, ex.getLocalizedMessage());
		}

	}

	@Override
	public Map<String, String> saveRequest(Request request) throws ServiceException {
		MessageLogger.debug(logger, String.format("saveRequest()"));
		try {
			return reqDao.saveRequest(request);
		} catch (DaoException ex) {
			MessageLogger.error(logger, "Error while retrieving saving request data", ex);
			throw new ServiceException(true, ex.getLocalizedMessage());
		}
	}

	@Override
	public List<Request> getAllCustomerRequests() throws ServiceException {
		MessageLogger.debug(logger, String.format("getAllCustomerRequests()"));
		try {
			return reqDao.getAllCustomerRequests();
		} catch (DaoException ex) {
			MessageLogger.error(logger, "Error while retrieving requests", ex);
			throw new ServiceException(true, ex.getLocalizedMessage());
		}
	}

}

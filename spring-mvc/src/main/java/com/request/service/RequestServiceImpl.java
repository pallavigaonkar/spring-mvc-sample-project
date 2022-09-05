package com.request.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.request.dao.RequestDao;
import com.request.dao.DaoException;
import com.util.MessageLogger;

@Service
public class RequestServiceImpl implements IRequestService {
	private static final Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);

	@Autowired
	RequestDao custDao;

	@Override
	public Map<String, Object> getRequestDataByCustomerId(String customerId) throws ServiceException {
		MessageLogger.debug(logger, String.format("getRequestDataByCustomerId(customerId = %s)", customerId));
		try {
			return custDao.getRequestDataByCustomerId(customerId);
		} catch (DaoException ex) {
			MessageLogger.error(logger, "Error while retrieving request data", ex);
			throw new ServiceException(true, ex.getLocalizedMessage());
		}

	}

}

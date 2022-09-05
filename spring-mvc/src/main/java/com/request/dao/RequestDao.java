package com.request.dao;

import java.util.Map;

public interface RequestDao {

	public Map<String, Object> getRequestDataByCustomerId(String customerId) throws DaoException;

}

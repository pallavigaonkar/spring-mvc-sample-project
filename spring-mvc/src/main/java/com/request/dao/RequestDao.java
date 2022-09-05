package com.request.dao;

import java.util.Map;

import com.request.model.Request;

public interface RequestDao {

	public Map<String, Object> getRequestDataByCustomerId(String customerId) throws DaoException;

	public Map<String, String> saveRequest(Request request) throws DaoException;

}

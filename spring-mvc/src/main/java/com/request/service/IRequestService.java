package com.request.service;

import java.util.Map;

import com.request.model.Request;
public interface IRequestService {
	
	public Map<String, Object> getRequestDataByCustomerId(String customerId) throws ServiceException;

	public Map<String, String> saveRequest(Request request) throws ServiceException;
	
}

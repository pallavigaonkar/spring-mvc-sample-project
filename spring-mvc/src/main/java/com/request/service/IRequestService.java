package com.request.service;

import java.util.Map;
public interface IRequestService {
	
	public Map<String, Object> getRequestDataByCustomerId(String customerId) throws ServiceException;
	
}

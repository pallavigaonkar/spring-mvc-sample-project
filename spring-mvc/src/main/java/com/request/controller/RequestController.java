package com.request.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.request.model.Request;
import com.request.model.ResponsePayload;
import com.request.service.IRequestService;
import com.request.service.ServiceException;
import com.util.MessageLogger;
import com.util.PayloadStatusEnum;

@Controller
@RequestMapping(value = "/requests")
public class RequestController {
	private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

	@Autowired
	IRequestService reqServiceImpl;

	@RequestMapping(value = "/", params="action=read", method = RequestMethod.GET)
	public @ResponseBody ResponsePayload getRequestDataByCustomerId(@RequestParam String customerId) {
		MessageLogger.debug(logger, String.format("getRequestDataByCustomerId(customerId = %s)", customerId));
		try {
			return new ResponsePayload("Request data retrieved successfully for customer id = "+customerId,
					reqServiceImpl.getRequestDataByCustomerId(customerId));
		} catch (ServiceException ex) {
			MessageLogger.error(logger, "Error while fetching request details for customer id = "+customerId, ex);
			return new ResponsePayload(PayloadStatusEnum.FAIL.getValue(), ex.getLocalizedMessage(),
					ex.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "/", params="action=create", method = RequestMethod.POST)
	public @ResponseBody ResponsePayload saveRequest(@Valid @RequestBody Request request) {
		MessageLogger.debug(logger, String.format("saveRequest()"));
		try {
			return new ResponsePayload("Request data saved successfully", reqServiceImpl.saveRequest(request));
		} catch (ServiceException ex) {
			MessageLogger.error(logger, "Error while saving request details", ex);
			return new ResponsePayload(PayloadStatusEnum.FAIL.getValue(), ex.getLocalizedMessage(),
					ex.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "/all", params="action=read", method = RequestMethod.GET)
	public @ResponseBody ResponsePayload getAllCustomerRequests() {
		MessageLogger.debug(logger, String.format("getAllCustomerRequests()"));
		try {
			return new ResponsePayload("Requests retrieved successfully ",
					reqServiceImpl.getAllCustomerRequests());
		} catch (ServiceException ex) {
			MessageLogger.error(logger, "Error while fetching requests ", ex);
			return new ResponsePayload(PayloadStatusEnum.FAIL.getValue(), ex.getLocalizedMessage(),
					ex.getLocalizedMessage());
		}
	}
}

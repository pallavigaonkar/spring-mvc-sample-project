package com.request.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.request.model.ResponsePayload;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {


	private static final Map<Integer, String> statusMsg;
	static {
		statusMsg = new HashMap<>();
		statusMsg.put(400,
				" Application encountered invalid parameters. Please contact your system administrator to report this issue at web server.");
		statusMsg.put(401, "You are not authorized to use this feature!");
		statusMsg.put(403,
				"Application could not process this request. Please try again by passing valid data or contact your system administrator to report this issue in  web server.");
		statusMsg.put(404,
				"The requested feature/resource/service is not available. Please contact your system administrator to report this issue in  web server.");
		statusMsg.put(405, "You are not authorized to use this feature!");
		statusMsg.put(406, "Not acceptable response due to invalid header response.!");
		statusMsg.put(407, "You are not authorized to use this feature!");
		statusMsg.put(408, "Request has been time out.Please try again!");
		statusMsg.put(409,
				"Conflicting standard Web server authority/security . Please contact your system administrator to report this issue.");
		statusMsg.put(410,
				"The requested feature/resource/service is not available. Please contact your system administrator to report this issue.");
		statusMsg.put(415,
				"Application encountered invalid parameters. Please contact your system administrator to report this issue at web server.");
		statusMsg.put(500, "Internal server error");
		statusMsg.put(501,
				"The Web site does not support the HTTP method, find in the HTTP data stream. Please contact your system administrator to report this issue.");
	}

	@RequestMapping(value = "/{code}", produces = "application/json")
	public @ResponseBody ResponsePayload error(@PathVariable("code") Integer code, HttpServletRequest request,
			HttpServletResponse response) {
		String msg = statusMsg.get(code);
		if (msg == null)
			msg = "Unexpected error";
		return new ResponsePayload(code, msg);
	}

}

package com.clinic.clinic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.clinic.clinic.config.DBInitializer;

/**
 * This is the controller class to return the view in case of forbidden
 * @author priyanshu.goyal
 *
 */
@Controller
public class AccessDeniedController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccessDeniedController.class);
	
	@GetMapping("/access")
	public String getAccessDenied()
	{
		logger.info("access denied to user");
		return "access";
	}

}

package com.clinic.clinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This is the controller class to return the view in case of forbidden
 * @author priyanshu.goyal
 *
 */
@Controller
public class AccessDeniedController {
	
	@GetMapping("/access")
	public String getAccessDenied()
	{
		return "access";
	}

}

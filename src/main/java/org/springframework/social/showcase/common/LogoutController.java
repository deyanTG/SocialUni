package org.springframework.social.showcase.common;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {

	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String redirectToPostSignOut( HttpServletRequest request) throws ServletException {
		request.logout();
		return "redirect:/";
	}
}

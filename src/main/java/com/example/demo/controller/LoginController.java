package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.User;
import com.example.demo.serviceImpl.UserService;

@Controller
@RequestMapping("/")
public class LoginController {

	private static final String LOGIN_VIEW = "login";
	private static final String HOME_VIEW = "home";
	
	@Autowired
	@Qualifier("userService")
	private UserService usuarioService;
	
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/auth/login");
	}
	@GetMapping("/home")
	public ModelAndView inicio(Model model) {
		ModelAndView mav = new ModelAndView(HOME_VIEW);
		return mav;
	}
	
	@GetMapping("/auth/login")
	public String login(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		model.addAttribute("user", new User());
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return LOGIN_VIEW;
	}

}

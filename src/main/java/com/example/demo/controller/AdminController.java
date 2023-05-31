package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.User;
import com.example.demo.model.LibroDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LibroService;
import com.example.demo.serviceImpl.UserService;

@Controller
@RequestMapping("/admin/users")
public class AdminController {

	private static final String USER_VIEW = "users";
	private static final String FORM_VIEW = "formUser";
	private static final String FORMPass_VIEW = "formPassword";
	private static final String FAVS_VIEW = "favLibros";
	
	// Inyectamos el servicio
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	@Autowired
	@Qualifier("librosService")
	private LibroService librosService;
	@Autowired
	@Qualifier("userRepository")
	private UserRepository usuarioRepository;

	// Mostrar usuarios
	@GetMapping("/listUsers")
	public ModelAndView listUsers() {
		ModelAndView mav = new ModelAndView(USER_VIEW);
		mav.addObject("users", userService.listAllUsuarios());
		return mav;
	}

	@PostMapping("/addUser")
	public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			RedirectAttributes flash, Model model) {
		userService.updateUser(user);
		flash.addFlashAttribute("success", "User updated successfully");
		return "redirect:/admin/users/listUsers";
	}
	@PostMapping("/addUserWP")
	public String addUserWP(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			RedirectAttributes flash, Model model) {
		userService.updateUserWP(user);
		flash.addFlashAttribute("success", "User updated successfully");
		return "redirect:/admin/users/listUsers";
	}
	@GetMapping("/formUser/{username}")
	public String formUser(@PathVariable(name = "username", required = false) String username, Model model) {
		model.addAttribute("user", userService.findUser(username));

		return FORM_VIEW;
	}
	
	@GetMapping("/formPassword/{username}")
	public String formPassword(@PathVariable(name = "username", required = false) String username, Model model) {
		model.addAttribute("user", userService.findUser(username));

		return FORMPass_VIEW;
	}
	
	// Metodo para borrar
	@GetMapping("/deleteUser/{id}")
	public String removeUser(@PathVariable("id") int id, RedirectAttributes flash) {
		User a = userService.findUserId(id);
		try {
			userService.deleteUser(a.getUsername());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flash.addFlashAttribute("success", "User deleted successfully");

		return "redirect:/admin/users/listUsers";
	}

	// Metodo de activar/descativar
	@GetMapping("/activateUser/{username}")
	public String activate(@PathVariable("username") String username, RedirectAttributes flash) {
		int i = userService.activate(username);
		if (i == 1) {
			flash.addFlashAttribute("success", "User activate successfully");
		} else if (i == 0) {
			flash.addFlashAttribute("success", "User deactivate successfully");
		} else
			flash.addFlashAttribute("error", "User can't be activate/deactivate");
		return "redirect:/admin/users/listUsers";
	}

	// Mostrar lista de favoritos de usuarios
		@GetMapping("/listFavs/{id}")
		public ModelAndView listUsers(@PathVariable int id, RedirectAttributes flash) {
			ModelAndView mav = new ModelAndView(FAVS_VIEW);
			List<Integer> listLibros = userService.getFavs(id);
			if(listLibros==null) {
				mav.addObject("libros", new ArrayList<LibroDTO>());
				return mav;
			}
			List<LibroDTO> listAllLibros = librosService.ListAllLibros();
			List<LibroDTO> librosFiltrados = listAllLibros.stream().filter(x-> listLibros.contains(x.getId())).collect(Collectors.toList());
			mav.addObject("libros", librosFiltrados);
			
			return mav;
		}
		
	// Metodo redirect
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/admin/users/listUsers");
	}

}

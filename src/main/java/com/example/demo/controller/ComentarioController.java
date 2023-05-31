package com.example.demo.controller;

import javax.validation.Valid;

import com.example.demo.entity.Comentario;
import com.example.demo.entity.User;
import com.example.demo.serviceImpl.UserService;
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

import com.example.demo.model.ComentarioDTO;
import com.example.demo.repository.ComentarioRepository;
import com.example.demo.service.ComentarioService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/comentarios")
public class ComentarioController {

	private static final String COMMENT_VIEW = "comentarios";
	private static final String FORM_VIEW = "formComentarios";
	
	@Autowired
	@Qualifier("comentarioService")
	private ComentarioService comentarioService;
	
	@Autowired
	@Qualifier("comentarioRepository")
	private ComentarioRepository comentarioRepository;

	@Autowired
	private UserService userService;
	
	// Metodo redirect
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/admin/comentarios/listComentarios/{id}");
	}
	
	//Metodo de borrar 
	@GetMapping("/deleteComentarios/{idLibro}/{id}")
	public String deleteComentario(@PathVariable("id")int id,@PathVariable("idLibro")int idLibro, RedirectAttributes flash) {
		if(comentarioService.removeComentarios(id)==0) {
			flash.addFlashAttribute("success","Comentario eliminado con éxito");	
		}else
			flash.addFlashAttribute("error","No se ha podido eliminar el comentario ");	
		
		return "redirect:/admin/comentarios/listComentarios/{idLibro}";
	}
	
	@PostMapping("/addComentario")
	public String addComentario(@Valid @ModelAttribute("comentario") ComentarioDTO ComentariosDTO, BindingResult bindingResult,
			RedirectAttributes flash, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("comentario", comentarioService.ListAllComentarios());
			return FORM_VIEW;
		} else {
			comentarioService.updateComentario(ComentariosDTO);
			flash.addFlashAttribute("success", "Comentario actualizado satisfactoriamente");
			return "redirect:/admin/comentarios/listComentarios/{id}";
			}
	}
	
	
	@GetMapping("/formComentario/{id}")
	public String formComentario(@PathVariable(name = "id", required = false) int id, Model model) {
		if (id + 1 == id) {
			model.addAttribute("id", comentarioService.findComentario(id));
		} else {
			return "/error/403";
		}
		return FORM_VIEW;
	}

	// Mostrar lista de comentarios de un libro
	@GetMapping("/listComentarios/{id}")
	public ModelAndView listComents(@PathVariable int id) {
		ModelAndView mav = new ModelAndView(COMMENT_VIEW);
		List<Comentario> listComentariosLibro = comentarioService.getComentariosLibro(id);
		System.out.println(listComentariosLibro);
		List<String> listUsername = new ArrayList<>();
		User u = new User();
		for(Comentario c : listComentariosLibro) {
			u = userService.findUserId(c.getIdUsuario().getId());
			listUsername.add(u.getUsername());
		}
		mav.addObject("comentarios", listComentariosLibro);
		mav.addObject("usernames", listUsername);
		mav.addObject("id", id);

		return mav;
	}
}

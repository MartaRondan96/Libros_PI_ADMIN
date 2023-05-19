package com.example.demo.controller;

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

import com.example.demo.model.ComentarioDTO;
import com.example.demo.repository.ComentarioRepository;
import com.example.demo.service.ComentarioService;

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
	
	// Metodo redirect
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/comentario/listComentario");
	}
	
	//Metodo de borrar 
	@GetMapping("/deleteComentarios/{id}")
	public String deleteComentario(@PathVariable("id")int id, RedirectAttributes flash) {
		if(comentarioService.removeComentarios(id)==0) {
			flash.addFlashAttribute("success","Comentario eliminado con éxito");	
		}else
			flash.addFlashAttribute("error","No se ha podido eliminar el comentario ");	
		
		return "redirect:/comentario/listComentario";
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
			return "redirect:/comentario/listComentario";		
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
	
	// Metodo para listar comentarios
		@GetMapping("/listComentarios")
		public ModelAndView listComentarios() {
			ModelAndView mav = new ModelAndView(COMMENT_VIEW);
			mav.addObject("lista",comentarioService.ListAllComentarios());
			return mav;
		}

}

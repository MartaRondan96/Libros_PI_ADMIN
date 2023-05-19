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

import com.example.demo.entity.Valoracion;
import com.example.demo.model.ValoracionDTO;
import com.example.demo.repository.ValoracionRepository;
import com.example.demo.service.ValoracionService;

@Controller
@RequestMapping("/admin/valoracion")
public class ValoracionController {
	private static final String VALORACION_VIEW = "valoracion";
	private static final String FORM_VIEW = "formValoracion";
	
	@Autowired
	@Qualifier("valoracionService")
	private ValoracionService valoracionService;
	
	@Autowired
	@Qualifier("valoracionRepository")
	private ValoracionRepository valoracionRepository;
	
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/valoracion/listValoracion");
	}
	
	//Metodo borrar valoracion
	@GetMapping("/deleteValoracion/{id}")
	public String deleteValoracion(@PathVariable("id")int id, RedirectAttributes flash) {
		if(valoracionService.removeValoracion(id)==0) {
			flash.addFlashAttribute("success","Valoracion eliminado con éxito");	
		}else
			flash.addFlashAttribute("error","No se ha podido eliminar el Valoracion");	
		
		return "redirect:/valoracion/listValoracion";
	}
	
	@PostMapping("/addValoracion")
	public String addValoracion(@Valid @ModelAttribute("valoracion")ValoracionDTO ValoracionDTO, BindingResult bindingResult,
			RedirectAttributes flash, Model model) {

		if(ValoracionDTO.getId()==0) {
			
			Valoracion n =valoracionRepository.findById(ValoracionDTO.getId());
			if(n!=null) {
				return "redirect:/valoracion/formValoracion?error";
				
			}else {
				valoracionService.addValoracion(ValoracionDTO);
				flash.addFlashAttribute("success","Valoracion creado con éxito");	
			}
				
		}else {
			valoracionService.updateValoracion(ValoracionDTO);
			flash.addFlashAttribute("success", "Valoracion modificado con éxito");
		}
		return "redirect:/valoracion/listValoracion";
	}
	
	
	@GetMapping("/formValoracion/{id}")
	public String formValoracion(@PathVariable(name = "id", required = false) int id, Model model) {
		if (id + 1 == id) {
			model.addAttribute("id", valoracionService.findValoracion(id));
		} else {
			return "/error/403";
		}
		return FORM_VIEW;
	}
	
	// Metodo para listar Valoracion
		@GetMapping("/listValoracion")
		public ModelAndView listValoracion() {
			ModelAndView mav = new ModelAndView(VALORACION_VIEW);
			mav.addObject("lista",valoracionService.ListAllValoracion());
			return mav;
		}
		
//		// Mostrar lista de valoraciomes de un libro
//		@GetMapping("/listValoracion/{id}")
//		public ModelAndView listUsers(@PathVariable int id) {
//			ModelAndView mav = new ModelAndView(COMMENT_VIEW);
//			List<Comentario> listComentariosLibro = comentarioService.getComentariosLibro(id);
//			mav.addObject("libros", listComentariosLibro);
//			mav.addObject("id", id);
//			return mav;
//		}
}

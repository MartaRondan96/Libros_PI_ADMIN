package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Comentario;
import com.example.demo.entity.Libro;
import com.example.demo.entity.PagActual;
import com.example.demo.entity.Valoracion;
import com.example.demo.model.LibroDTO;
import com.example.demo.model.PagActualDTO;
import com.example.demo.model.ValoracionDTO;
import com.example.demo.repository.PagActualRepository;
import com.example.demo.service.PagActualService;

@Controller
@RequestMapping("/admin/pag")
public class PagActualController {
	private static final String PAG_VIEW = "pag";
	private static final String FORM_VIEW = "formPag";
	
	@Autowired
	private PagActualService pagActualService;
	
	@Autowired
	private PagActualRepository pagRepository;
	
	// Metodo redirect
	//Eliminar despues del test
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/pag/listLibros");
	}
	
	//Metodo borrar pagina actual
	@GetMapping("/deletePag/{id}")
	public String deletePagina(@PathVariable("id")int id, RedirectAttributes flash) {
		if(pagActualService.removePagActual(id)==0) {
			flash.addFlashAttribute("success","Página actual eliminada con éxito");	
		}else
			flash.addFlashAttribute("error","No se ha podido eliminar la página.");	
		
		return "redirect:/pag/listPag";
	}
		
	@PostMapping("/addPag")
	public String addPag(@Valid @ModelAttribute("pag")PagActualDTO PagActualDTO, BindingResult bindingResult,
			RedirectAttributes flash, Model model) {

		if(PagActualDTO.getId()==0) {
			
			PagActualDTO p =pagActualService.findPagActual(PagActualDTO.getId());
			if(p!=null) {
				return "redirect:/pag/formPag?error";
				
			}else {
				pagActualService.addPagActual(PagActualDTO);
				flash.addFlashAttribute("success","PagActual creado con éxito");	
			}
				
		}else {
			pagActualService.updatePagActual(PagActualDTO);
			flash.addFlashAttribute("success", "PagActual modificado con éxito");
		}
		return "redirect:/pag/listPag";
	}
		
	
	@GetMapping("/formValoracion/{id}")
	public String formValoracion(@PathVariable(name = "id", required = false) int id, Model model) {
		if (id + 1 == id) {
			model.addAttribute("id", pagActualService.findPagActual(id));
		} else {
			return "/error/403";
		}
		return FORM_VIEW;
	}
		
	@GetMapping("/listPagActual")
	public ModelAndView listPagActual() {
		ModelAndView mav = new ModelAndView(PAG_VIEW);
		mav.addObject("lista",pagActualService.ListAllPagActual());
		return mav;
	}

}

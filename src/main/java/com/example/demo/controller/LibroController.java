package com.example.demo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.demo.entity.User;
import com.example.demo.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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
import com.example.demo.model.LibroDTO;
import com.example.demo.repository.LibroRepository;
import com.example.demo.service.ComentarioService;
import com.example.demo.service.LibroService;
import com.example.demo.upload.StorageService;

@Controller
@RequestMapping("/admin/libros")
public class LibroController {
	private static final String BOOKS_VIEW = "books";
	private static final String FORM_VIEW = "formBooks";
	
	@Autowired
	@Qualifier("librosService")
	private LibroService librosService;
	
	@Autowired
	@Qualifier("librosRepository")
	private LibroRepository librosRepository;
	
	@Autowired
	@Qualifier("comentarioService")
	private ComentarioService comentarioService;

	@Autowired
	@Qualifier("storageService")
	private StorageService storageService;
	// Metodo redirect
	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/libros/listLibros");
	}
	
	//Metodo de borrar 
	@GetMapping("/deleteLibros/{id}")
	public String deleteBook(@PathVariable("id")int id, RedirectAttributes flash) {

		File foto=new File("http://localhost:8080/images/"+librosService.findLibro(id).getImagen());
	
		if(foto.exists()) {
			System.out.println(foto.getAbsolutePath());
			foto.delete();
		}
		if(librosService.removeLibro(id)==0) {
			flash.addFlashAttribute("success","Libro eliminado con éxito");	
		}else
			flash.addFlashAttribute("error","No se ha podido eliminar el libro");	
		
		return "redirect:/admin/libros/listLibros";
	}
	
	@PostMapping("/addLibro")
	public String addLibro(@Valid @ModelAttribute("libro") LibroDTO LibrosDTO, BindingResult bindingResult,
			RedirectAttributes flash, Model model, @RequestParam("file")MultipartFile file) {
		String imagen=storageService.store(file,LibrosDTO.getTitulo());
		LibrosDTO.setImagen(imagen);
		if(LibrosDTO.getId()==0) {
			Libro n =librosRepository.findById(LibrosDTO.getId());
			if(n!=null) {
				return "redirect:/admin/libros/formLibro?error";
			}else {
				librosService.addLibro(LibrosDTO);
				flash.addFlashAttribute("success","Libro creado con éxito");	
			}
		}else {
			librosService.updateLibro(LibrosDTO);
			flash.addFlashAttribute("success", "Libro modificado con éxito");
		}
		return "redirect:/admin/libros/listLibros";
	}
	
	
	@GetMapping(value = { "/formLibro", "/formLibro/{id}" })
	public String formLibro(@PathVariable(name = "id", required = false) Integer id, Model model) {
		if (id != null) {
			File foto=new File("http://localhost:8080/images/"+librosService.findLibro(id).getImagen());
			model.addAttribute("libro", librosService.findLibro(id));
		} else {
			model.addAttribute("libro", new Libro());
		}
		return FORM_VIEW;
	}
	
	// Metodo para listar libros
	@GetMapping("/listLibros")
	public ModelAndView listLibros(@RequestParam(defaultValue = "0") int page,
								   @RequestParam(defaultValue = "10") int size) {
		Page<LibroDTO> libroPage = librosService.ListAllLibrosPaginado(page, size);
		ModelAndView mav = new ModelAndView(BOOKS_VIEW);
		mav.addObject("books", libroPage.getContent());
		mav.addObject("currentPage", page);
		mav.addObject("totalPages", libroPage.getTotalPages());
		mav.addObject("totalItems", libroPage.getTotalElements());
		return mav;
	}
		


}

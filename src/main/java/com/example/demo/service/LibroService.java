package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Libro;
import com.example.demo.model.LibroDTO;
import org.springframework.data.domain.Page;

public interface LibroService {
	public abstract Page<LibroDTO> ListAllLibrosPaginado(int page, int size);
	public abstract List<LibroDTO> ListAllLibros();
	public abstract Libro addLibro(LibroDTO LibroDTO);
	public abstract int removeLibro(int id);
	public abstract Libro updateLibro(LibroDTO LibroDTO);
	public abstract Libro transform(LibroDTO LibroDTO);
	public abstract LibroDTO transform(Libro Libro);
	public abstract LibroDTO findLibro(int id);
	
}

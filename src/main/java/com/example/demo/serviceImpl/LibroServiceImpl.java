package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Libro;
import com.example.demo.model.LibroDTO;
import com.example.demo.repository.LibroRepository;
import com.example.demo.service.LibroService;
@Service("librosService")
public class LibroServiceImpl implements LibroService {
	
	@Autowired
	@Qualifier("librosRepository")
	private LibroRepository librosRepository;

	@Override
	public Page<LibroDTO> ListAllLibrosPaginado(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Libro> libroPage = librosRepository.findAll(pageable);
		return libroPage.map(this::transform);
	}
	@Override
	public List<LibroDTO> ListAllLibros() {
		return librosRepository.findAll().stream()
				.map(c->transform(c)).collect(Collectors.toList());
	}
	@Override
	public Libro addLibro(LibroDTO LibroDTO) {
		return librosRepository.save(transform(LibroDTO));
		}

	@Override
	public int removeLibro(int id) {
		librosRepository.deleteById(id);
		return 0;
	}

	@Override
	public Libro updateLibro(LibroDTO LibroDTO) {
		return librosRepository.save(transform(LibroDTO));
	}

	@Override
	public Libro transform(LibroDTO LibroDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(LibroDTO, Libro.class);
	}

	@Override
	public LibroDTO transform(Libro Libro) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(Libro, LibroDTO.class);
	}
	@Override
	public LibroDTO findLibro(int id) {
		return transform(librosRepository.findById(id));
	}

}

package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Comentario;
import com.example.demo.entity.Libro;
import com.example.demo.model.ComentarioDTO;
import com.example.demo.repository.ComentarioRepository;
import com.example.demo.repository.LibroRepository;
import com.example.demo.service.ComentarioService;

@Service("comentarioService")
public class ComentarioServiceImpl implements ComentarioService {

	@Autowired
	@Qualifier("comentarioRepository")
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	@Qualifier("librosRepository")
	private LibroRepository librosRepository; 
	@Override
	public List<ComentarioDTO> ListAllComentarios() {
		return comentarioRepository.findAll().stream()
				.map(c->transform(c)).collect(Collectors.toList());
	}

	@Override
	public Comentario addComentario(ComentarioDTO ComentarioDTO) {
		return comentarioRepository.save(transform(ComentarioDTO));
		}

	@Override
	public int removeComentarios(int id) {
		comentarioRepository.deleteById(id);
		return 0;
		}

	@Override
	public Comentario updateComentario(ComentarioDTO ComentariosDTO) {
		return comentarioRepository.save(transform(ComentariosDTO));
		
	}

	@Override
	public Comentario transform(ComentarioDTO ComentariosDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(ComentariosDTO, Comentario.class);
	}

	@Override
	public ComentarioDTO transform(Comentario Comentario) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(Comentario, ComentarioDTO.class);
	}

	@Override
	public ComentarioDTO findComentario(int id) {
		return transform(comentarioRepository.findById(id));
	}
	@Override
	public List<Comentario> getComentariosLibro(int id) {
		Libro l = librosRepository.findById(id);
		List<Comentario> list = l.getComentariosList();
		return list;
	}

}

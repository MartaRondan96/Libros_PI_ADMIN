package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Libro;
import com.example.demo.entity.Valoracion;
import com.example.demo.model.LibroDTO;
import com.example.demo.model.ValoracionDTO;
import com.example.demo.repository.ValoracionRepository;
import com.example.demo.service.LibroService;
import com.example.demo.service.ValoracionService;
@Service("valoracionService")
public class ValoracionServiceImpl implements ValoracionService {
	@Autowired
	@Qualifier("valoracionRepository")
	private ValoracionRepository valoracionRepository;
	@Autowired
	@Qualifier("librosService")
	private LibroService libroService;

	@Override
	public List<ValoracionDTO> ListAllValoracion() {
		return valoracionRepository.findAll().stream()
				.map(c->transform(c)).collect(Collectors.toList());
	}

	@Override
	public Valoracion addValoracion(ValoracionDTO valoracionDTO) {
		valoracionRepository.save(transform(valoracionDTO));
		List<Valoracion> notaList = getListNotaByIdLibro(valoracionDTO.getIdLibro().getId());
		float nMedia=0F;
		for(Valoracion v : notaList) 
			nMedia+=v.getPuntos();
		float media = nMedia/notaList.size();
		LibroDTO libroAct = libroService.findLibro(valoracionDTO.getIdLibro().getId());
		libroAct.setNota(media);
		libroService.updateLibro(libroAct);
		return transform(valoracionDTO);
	}

	@Override
	public int removeValoracion(int id) {
		valoracionRepository.deleteById(id);
		return 0;
	}

	@Override
	public Valoracion updateValoracion(ValoracionDTO valoracionDTO) {
		valoracionRepository.save(transform(valoracionDTO));
		List<Valoracion> notaList = getListNotaByIdLibro(valoracionDTO.getIdLibro().getId());
		float nMedia=0F;
		for(Valoracion v : notaList) 
			nMedia+=v.getPuntos();
		float media = nMedia/notaList.size();
		LibroDTO libroAct = libroService.findLibro(valoracionDTO.getIdLibro().getId());
		libroAct.setNota(media);
		libroService.updateLibro(libroAct);
		return transform(valoracionDTO);
		
	}

	@Override
	public Valoracion transform(ValoracionDTO valoracionDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(valoracionDTO, Valoracion.class);
	}

	@Override
	public ValoracionDTO transform(Valoracion valoracion) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(valoracion, ValoracionDTO.class);
	}

	@Override
	public List<Valoracion> findValoracionByIdLibro(int idLibro) {
		return valoracionRepository.findByIdLibro(idLibro);
	}

	@Override
	public ValoracionDTO findValoracion(int id) {
		return transform(valoracionRepository.findById(id));
	}

	@Override
	public List<Valoracion> getListNotaByIdLibro(int idLibro) {
		List<Valoracion> listNota = valoracionRepository.findByIdLibro(idLibro);
		return listNota;
	}
}

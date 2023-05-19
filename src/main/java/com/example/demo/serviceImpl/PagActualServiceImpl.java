package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.PagActual;
import com.example.demo.model.PagActualDTO;
import com.example.demo.repository.PagActualRepository;
import com.example.demo.service.PagActualService;

@Service
public class PagActualServiceImpl implements PagActualService{

	@Autowired
	private PagActualRepository pagActualRepository;
	
	@Override
	public List<PagActualDTO> ListAllPagActual() {
		return pagActualRepository.findAll().stream()
				.map(c->transform(c)).collect(Collectors.toList());
	}

	@Override
	public PagActual addPagActual(PagActualDTO PagActualDTO) {
		return pagActualRepository.save(transform(PagActualDTO));
	}

	@Override
	public int removePagActual(int id) {
		pagActualRepository.deleteById(id);
		return 0;
	}

	@Override
	public PagActual updatePagActual(PagActualDTO PagActualDTO) {
		return pagActualRepository.save(transform(PagActualDTO));
	}

	@Override
	public PagActual transform(PagActualDTO PagActualDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(PagActualDTO, PagActual.class);
	}

	@Override
	public PagActualDTO transform(PagActual pagActual) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(pagActual, PagActualDTO.class);
	}

	@Override
	public PagActualDTO findPagActual(int id) {
		return transform(pagActualRepository.findById(id));
	}
	
	@Override
	public PagActualDTO findPagActualIdLibro(int idLibro) {
		return transform(pagActualRepository.findByIdLibro(idLibro));
	}
	
	@Override
	public PagActualDTO findPagActualIdUsuario(int idUsuario) {
		return transform(pagActualRepository.findByIdUsuario(idUsuario));
	}
	
	@Override
	public PagActualDTO findPagActualIdUsuarioAndIdLibro(int idUsuario, int idLibro) {
		return transform(pagActualRepository.findByIdUsuarioAndIdLibro(idUsuario, idLibro));
	}
}

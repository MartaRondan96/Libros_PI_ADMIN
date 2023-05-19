package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.PagActual;
import com.example.demo.model.PagActualDTO;

public interface PagActualService {
	public abstract List<PagActualDTO> ListAllPagActual();
	public abstract PagActual addPagActual(PagActualDTO PagActualDTO);
	public abstract int removePagActual(int id);
	public abstract PagActual updatePagActual(PagActualDTO PagActualDTO);
	public abstract PagActual transform(PagActualDTO PagActualDTO);
	public abstract PagActualDTO transform(PagActual pagActual);
	public abstract PagActualDTO findPagActual(int id);
	public abstract PagActualDTO findPagActualIdLibro(int idLibro);
	public abstract PagActualDTO findPagActualIdUsuario(int idUsuario);
	public abstract PagActualDTO findPagActualIdUsuarioAndIdLibro(int idUsuario, int idLibro);
}

package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Valoracion;
import com.example.demo.model.ValoracionDTO;

public interface ValoracionService {
	public abstract List<ValoracionDTO> ListAllValoracion();
	public abstract Valoracion addValoracion(ValoracionDTO valoracionDTO);
	public abstract int removeValoracion(int id);
	public abstract Valoracion updateValoracion(ValoracionDTO valoracionDTO);
	public abstract Valoracion transform(ValoracionDTO valoracionDTO);
	public abstract ValoracionDTO transform(Valoracion valoracion);
	public abstract List<Valoracion> findValoracionByIdLibro(int idLibro);
	public abstract ValoracionDTO findValoracion(int id);
	public abstract List<Valoracion> getListNotaByIdLibro(int idLibro);
}

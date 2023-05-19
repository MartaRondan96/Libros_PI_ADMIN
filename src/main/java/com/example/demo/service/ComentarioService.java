package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Comentario;
import com.example.demo.model.ComentarioDTO;

public interface ComentarioService {
	public abstract List<ComentarioDTO> ListAllComentarios();
	public abstract Comentario addComentario(ComentarioDTO ComentarioDTO);
	public abstract int removeComentarios(int id);
	public abstract Comentario updateComentario(ComentarioDTO ComentariosDTO);
	public abstract Comentario transform(ComentarioDTO ComentariosDTO);
	public abstract ComentarioDTO transform(Comentario Comentario);
	public abstract ComentarioDTO findComentario(int id);
	public abstract List<Comentario> getComentariosLibro(int id);
}

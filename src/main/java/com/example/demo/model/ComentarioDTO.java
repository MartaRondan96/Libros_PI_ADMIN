package com.example.demo.model;

public class ComentarioDTO {
	private String id;
	private int idUsuario;
	private String comentario;
	private int idLibro;
	public ComentarioDTO(String id, int idUsuario, String comentario, int idLibro) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.comentario = comentario;
		this.idLibro = idLibro;
	}
	public ComentarioDTO() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}
	@Override
	public String toString() {
		return "ComentariosDTO [id=" + id + ", idUsuario=" + idUsuario + ", comentario=" + comentario + ", idLibro="
				+ idLibro + "]";
	}
	
	
}

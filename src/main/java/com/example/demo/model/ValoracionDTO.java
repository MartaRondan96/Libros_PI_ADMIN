package com.example.demo.model;

import com.example.demo.entity.Libro;
import com.example.demo.entity.User;

public class ValoracionDTO {
	
	private int id;
    private User idUsuario;
    private Libro idLibro;
	private float puntos;
	
	public ValoracionDTO() {
		super();
	}
	public ValoracionDTO(int id, User idUsuario, Libro idLibro, float puntos) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.idLibro = idLibro;
		this.puntos = puntos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(User idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Libro getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(Libro idLibro) {
		this.idLibro = idLibro;
	}
	public float getPuntos() {
		return puntos;
	}
	public void setPuntos(float puntos) {
		this.puntos = puntos;
	}
	@Override
	public String toString() {
		return "Valoraciones [id=" + id + ", idUsuario=" + idUsuario + ", idLibro=" + idLibro + ", puntos=" + puntos
				+ "]";
	}
}

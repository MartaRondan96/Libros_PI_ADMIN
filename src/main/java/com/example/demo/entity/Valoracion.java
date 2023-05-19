package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Valoracion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
    @JoinColumn(name = "idUsuario")
    private User idUsuario;
	@ManyToOne
    @JoinColumn(name = "idLibro")
    private Libro idLibro;
	private float puntos;
	
	public Valoracion() {
		super();
	}
	public Valoracion(int id, User idUsuario, Libro idLibro, float puntos) {
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

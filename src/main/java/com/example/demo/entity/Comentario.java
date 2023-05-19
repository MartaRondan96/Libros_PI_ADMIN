package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comentario{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;	
	private String comentario;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private User idUsuario;
	
	@ManyToOne
	@JoinColumn(name = "idLibro")
	private Libro idLibro;

	public Comentario() {
		super();
	}

	public Comentario(int id, String comentario, User idUsuario, Libro idLibro) {
		super();
		this.id = id;
		this.comentario = comentario;
		this.idUsuario = idUsuario;
		this.idLibro = idLibro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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

	@Override
	public String toString() {
		return "Comentarios [id=" + id + ", comentario=" + comentario + ", idUsuario=" + idUsuario + ", idLibro="
				+ idLibro + "]";
	}	
}

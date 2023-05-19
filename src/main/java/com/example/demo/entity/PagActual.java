package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PagActual {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne()
	@JoinColumn(name="idUsuario")
	private User idUsuario;
	@ManyToOne()
	@JoinColumn(name="idLibro")
	private Libro idLibro;
	private int num_Pag;
	public PagActual(int id, User idUsuario, Libro idLibro, int num_Pag) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.idLibro = idLibro;
		this.num_Pag = num_Pag;
	}
	public PagActual() {
		super();
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
	public int getNum_Pag() {
		return num_Pag;
	}
	public void setNum_Pag(int num_Pag) {
		this.num_Pag = num_Pag;
	}
	@Override
	public String toString() {
		return "PagActual [id=" + id + ", idUsuario=" + idUsuario + ", idLibro=" + idLibro + ", num_Pag=" + num_Pag
				+ "]";
	}
	
}

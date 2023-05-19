package com.example.demo.model;

import com.example.demo.entity.Libro;
import com.example.demo.entity.User;

public class PagActualDTO {
	private int id;
	private User idUsuario;
	private Libro idLibro;
	private int num_Pag;
	public PagActualDTO() {
		super();
	}
	public PagActualDTO(int id, User idUsuario, Libro idLibro, int num_Pag) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.idLibro = idLibro;
		this.num_Pag = num_Pag;
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
		return "PagActualDTO [id=" + id + ", idUsuario=" + idUsuario + ", idLibro=" + idLibro + ", num_Pag=" + num_Pag
				+ "]";
	}
	
}

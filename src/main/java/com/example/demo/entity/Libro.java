package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Libro {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String titulo;
	private String autor;
	private String ISBN;
	private int pag;
	private float nota;
	private String imagen;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idLibro")
    private List<Comentario> comentariosList;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idLibro")
    private List<Valoracion> listValoraciones;
	
	public Libro() {
		super();
	}

	public Libro(int id, String titulo, String autor, String iSBN, int pag, float nota, String imagen,
			List<Comentario> comentariosList, List<Valoracion> listValoraciones) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		ISBN = iSBN;
		this.pag = pag;
		this.nota = nota;
		this.imagen = imagen;
		this.comentariosList = comentariosList;
		this.listValoraciones = listValoraciones;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getPag() {
		return pag;
	}

	public void setPag(int pag) {
		this.pag = pag;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<Comentario> getComentariosList() {
		return comentariosList;
	}

	public void setComentariosList(List<Comentario> comentariosList) {
		this.comentariosList = comentariosList;
	}

	public List<Valoracion> getListValoraciones() {
		return listValoraciones;
	}

	public void setListValoraciones(List<Valoracion> listValoraciones) {
		this.listValoraciones = listValoraciones;
	}

	@Override
	public String toString() {
		return "Libros [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", ISBN=" + ISBN + ", pag=" + pag
				+ ", nota=" + nota + ", imagen=" + imagen + ", comentariosList=" + comentariosList
				+ ", listValoraciones=" + listValoraciones + "]";
	}
}

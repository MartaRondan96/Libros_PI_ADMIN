package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PagActual;

public interface PagActualRepository extends JpaRepository<PagActual, Serializable> {
	public abstract PagActual findById(int id);
	public abstract PagActual findByIdLibro(int idLibro);
	public abstract PagActual findByIdUsuario(int idUsuario);
	public abstract PagActual findByIdUsuarioAndIdLibro(int idUsuario, int idLibro);
}

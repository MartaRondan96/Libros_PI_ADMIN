package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Valoracion;
@Repository("valoracionRepository")
public interface ValoracionRepository extends JpaRepository<Valoracion, Serializable>{
	public abstract List<Valoracion> findByIdLibro(int idLibro);
	public abstract Valoracion findById(int idLibro);
}

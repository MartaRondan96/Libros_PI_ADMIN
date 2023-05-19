package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Libro;

@Repository("librosRepository")
public interface LibroRepository extends JpaRepository<Libro, Serializable>{

	public abstract Libro findById(int id);

}

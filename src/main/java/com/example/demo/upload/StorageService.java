package com.example.demo.upload;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.nio.file.Path;
import java.util.stream.Stream;

//Metodos servicios para el almacenamiento de ficheros
public interface StorageService {

	void init();

	String store(MultipartFile file, String name);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();

}
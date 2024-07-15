package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {

    @Query("SELECT t FROM Libro t")
    List<Libro> mostrarLibros();

    @Query("SELECT a FROM Autor a")
    List<Autor> mostrarAutores();
}

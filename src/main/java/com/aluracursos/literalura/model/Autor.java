package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor () {}

    public Autor(DatosAutor autores, Libro libro) {
        this.nombre = autores.nombre();
        this.fechaFallecimiento = autores.fechaFalleimiento();
        this.fechaNacimiento = autores.fechaNacimiento();
        this.libros.add(libro);
    }

    public void addLibro(Libro libro){
        this.libros.add(libro);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {

        return """
                Autor: %s
                Fecha de nacimiento: %d
                Fecha de fallecimiento: %d
                Libros: %s
                """.formatted(this.nombre, this.fechaNacimiento, this.fechaFallecimiento,
                this.libros.stream().map(Libro::getTitulo).toList().toString()
        );
    }
}

package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Libro {
    @Id
    private Long id;
    @Column(unique = true)
    private String titulo;
    private List<String> idiomas;
    private Integer descargas;
    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    public Libro (){}

    public Libro(DatosResultado datos) {
        DatosLibro primerLibro = datos.results().get(0);
        this.id = (long) primerLibro.id();
        this.titulo = primerLibro.title();
        this.idiomas = primerLibro.idiomas();
        this.autor = new Autor(primerLibro.autores().get(0), this);
        this.descargas = primerLibro.descargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public String toString (){
        return """
                --------Libro-------
                Titulo: %s
                Autor: %s
                Idioma: %s
                Descargas: %d
                --------------------
                """.formatted(titulo,autor.getNombre(),idiomas, descargas);

    }
}

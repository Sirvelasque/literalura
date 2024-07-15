package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.model.DatosResultado;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books?search=";
    private LibroRepository libroRepository;

    private Optional<Libro> libroBuscado;

    public Principal(LibroRepository repository) {
        this.libroRepository = repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void listarPorIdioma() {
        System.out.println("Ingrese el idioma para buscar los libros:");
        System.out.println("""
                es: Español
                en: Inglés
                """);
        String idioma = teclado.nextLine();
        List<Libro> libros = libroRepository.listarPorIdioma(idioma);
        if (libros.isEmpty()){
            System.out.println("No hay libros en el idioma seleccionado");
        }
        libros.forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        Integer year = 0;

        while (year == null || year < 1000 || year > 2024) {
            System.out.println("Por favor, ingrese un año entre 1000 y 2024:");
            if (teclado.hasNextInt()) {
                year = teclado.nextInt();
                if (year < 1000 || year > 2024) {
                    System.out.println("El año debe estar entre 1000 y 2024. Inténtelo de nuevo.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                teclado.next(); // Descartar entrada no válida
            }
            teclado.nextLine(); // Limpiar el buffer del scanner
        }

        List<Autor> autores = libroRepository.listarvivos(year);
        if (autores.isEmpty()){
            System.out.println("Sin resultados");
        }
        autores.forEach(System.out::println);
    }

    private void listarAutores() {
        libroRepository.mostrarAutores().forEach(System.out::println);
    }

    private void listarLibros() {
        libroRepository.mostrarLibros().forEach(System.out::println);
    }

    private DatosResultado getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        System.out.println(URL_BASE + nombreLibro.replace(" ", "%20"));
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
        DatosResultado datos = conversor.obtenerDatos(json, DatosResultado.class);
        return datos;
    }

    private void buscarLibro() {
        DatosResultado datos = getDatosLibro();

        Libro libro = new Libro(datos);
        libroRepository.save(libro);
        System.out.println(libro.toString());
    }
}


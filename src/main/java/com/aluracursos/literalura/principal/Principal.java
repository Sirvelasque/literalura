package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.model.DatosResultado;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;
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
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
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


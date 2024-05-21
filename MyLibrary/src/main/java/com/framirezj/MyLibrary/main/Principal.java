package com.framirezj.MyLibrary.main;

import com.framirezj.MyLibrary.api.ConsumoApi;
import com.framirezj.MyLibrary.model.Autor;
import com.framirezj.MyLibrary.model.Libro;
import com.framirezj.MyLibrary.record.DatosApi;
import com.framirezj.MyLibrary.repository.AutorRepository;
import com.framirezj.MyLibrary.repository.LibroIdiomaCount;
import com.framirezj.MyLibrary.repository.LibroRepository;
import com.framirezj.MyLibrary.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    Scanner teclado = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/";
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConvierteDatos convierteDatos = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    //constructor
    public Principal() {
    }

    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.libroRepository = repository;
        this.autorRepository = autorRepository;
    }


    //METODO DE LA OPCION 1 PARA BUSCAR UN LIBRO
    public void buscarYGuardarLibro() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        String libroSolicitado = teclado.nextLine();

        //llamada a la api por titulo
        String json = consumoApi.obtenerDatos(URL_BASE + "?search=" + libroSolicitado.replace(" ", "+"));
        DatosApi datosApi = convierteDatos.obtenerDatos(json, DatosApi.class);

        Optional<Libro> libroEncontrado = datosApi.libros().stream()
                .map(Libro::new)
                .findFirst();


        if (libroEncontrado.isPresent()) {

            Autor autor = autorRepository.findByNombreContainsIgnoreCase(libroEncontrado.get().getAutor().getNombre());

            if (autor == null) {
                // Si el autor no existe, créalo y asígnalo al libro
                Autor nuevoAutor = libroEncontrado.get().getAutor();
                //nuevoAutor.setNombre(nombreAutor);

                //me retorna el autor con el id, entonces si paso el autor al libro con el id, al guardarlo no me duplicara el autor!!!!
                autor = autorRepository.save(nuevoAutor);
            }

            Libro libro = libroEncontrado.get();

            try {
                libro.setAutor(autor);
                libroRepository.save(libro);
                System.out.println(libro);
            } catch (DataIntegrityViolationException ex) {
                // Manejar la excepción de restricción única
                System.out.println("El libro con este título ya existe en la base de datos.");
            }

        } else {
            System.out.println("el libro no se encuentra.");
        }

    }

    //METODO DE LA OPCION 2 PARA LISTAR LOS LIBROS DE LA BD
    public void listarLibros() {
        List<Libro> libros = libroRepository.findAll();
        libros.stream()
                .forEach(System.out::println);
    }

    //METODO DE LA OPCION 3 PARA LISTAR LOS AUTORES GUARDADOS EN LA BD
    public void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        autores.stream().forEach(System.out::println);
    }

    //METODO DE LA OPCION 4 PARA LISTAR LOS AUTORES VIVOS SEGUN AÑO
    public void listarAutoresVivos() {
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar: (Ejemplo: 1559)");
        int fechaBuscada;
        String fecha;

        try {
            fechaBuscada = teclado.nextInt();

            fecha = String.valueOf(fechaBuscada);

            List<Autor> autoresVivos = autorRepository.buscarAutorVivo(fecha);

            if (autoresVivos.isEmpty()) {
                System.out.println("No se encontraron registros :'(.");
            } else {
                autoresVivos.stream().forEach(System.out::println);
            }

        } catch (Exception e) {
            System.out.println("Escriba una año valido, Ejemplo 1600");
            teclado.nextLine();
        }

    }

    //METODO OPCION 5 PARA LISTAR LIBROS POR IDIOMA
    public void listarIdiomas() {
        List<LibroIdiomaCount> idiomas = libroRepository.buscarIdiomasCount();
        idiomas.stream().forEach(i -> System.out.println(
                """
                        Codigo idioma: %s, Cantidad de libros: %d""".formatted(i.getIdioma(), i.getCount())
        ));

        System.out.println("Ingresa el codigo de idioma para listar los libros: (Ejemplo: es)");

        try {
            String codigo = teclado.nextLine();

            for (LibroIdiomaCount idioma : idiomas) {
                if (idioma.getIdioma().equals(codigo)) {
                    libroRepository.findByIdiomaEquals(codigo).stream().forEach(System.out::println);
                    return;
                } else if (codigo.length() > 2) {
                    throw new InputMismatchException("Los Codigos contiene 2 caracteres, Ejemplo: es");
                }
            }
            System.out.println("Codigo invalido!");
        } catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }

    }

    //METODO OPCION 6
    public void top10Descargas(){
        System.out.println("Los TOP 10 con mas descargas son:");
        List<Libro> librosTop10 = libroRepository.findTop10ByOrderByCantidadDeDescargasDesc();
        librosTop10.stream().forEach(System.out::println);
    }

    //MENU
    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ########### - MENU - ###########
                    1 - Buscar libro por título.
                    2 - Listar libros registrados.
                    3 - Listar autores registrados.
                    4 - Listar autores vivos en un determinado año.
                    5 - Listar libros por idioma
                    Extras-------
                    6 - Listar el TOP 10 Libros con mas descargas.
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarYGuardarLibro();
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
                    listarIdiomas();
                    break;
                case 6:
                    top10Descargas();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }


}

package com.framirezj.MyLibrary.main;


import com.framirezj.MyLibrary.api.ConsumoApi;
import com.framirezj.MyLibrary.model.Autor;
import com.framirezj.MyLibrary.model.Libro;
import com.framirezj.MyLibrary.record.DatosApi;
import com.framirezj.MyLibrary.repository.AutorRepository;
import com.framirezj.MyLibrary.repository.LibroRepository;
import com.framirezj.MyLibrary.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

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
    public Principal(){}

    public Principal(LibroRepository repository, AutorRepository autorRepository){
        this.libroRepository = repository;
        this.autorRepository = autorRepository;
    }


    //METODO DE LA OPCION 1 PARA BUSCAR UN LIBRO
    public void buscarYGuardarLibro(){
        System.out.println("Ingrese el nombre del libro que desea buscar");
        String libroSolicitado = teclado.nextLine();

        //llamada a la api por titulo
        String json = consumoApi.obtenerDatos(URL_BASE +"?search="+ libroSolicitado.replace(" ", "+"));
        DatosApi datosApi = convierteDatos.obtenerDatos(json, DatosApi.class);

        Optional<Libro> libroEncontrado = datosApi.libros().stream()
                .map(Libro::new)
                .findFirst();



        if (libroEncontrado.isPresent()){

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
            } catch (DataIntegrityViolationException ex) {
                // Manejar la excepción de restricción única
                System.out.println("El libro con este título ya existe en la base de datos.");
            }






        }else{
            System.out.println("el libro no se encuentra.");
        }

    }

    //METODO DE LA OPCION 2 PARA LISTAR LOS LIBROS DE LA BD
    public void listarLibros(){
        List<Libro> libros = libroRepository.findAll();
        libros.stream()
                .forEach(System.out::println);
    }


    //MENU
    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por título.
                    2 - Listar libros registrados.
                    3 - Listar autores registrados.
                    4 - Listar autores vivos en un determinado año.
                    5 - Listar libros por idioma
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
                    //mostrarSeriesBuscadas();
                    break;
                case 4:
                    //buscarSeriesPorTitulo();
                    break;
                case 5:
                    //mostrarTop5Series();
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

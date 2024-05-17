package com.framirezj.MyLibrary.main;


import com.framirezj.MyLibrary.api.ConsumoApi;
import com.framirezj.MyLibrary.model.DatosApi;
import com.framirezj.MyLibrary.model.DatosLibros;
import com.framirezj.MyLibrary.service.ConvierteDatos;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private static final String URL_API = "https://gutendex.com/books/";
    private ConsumoApi consumoApi = new ConsumoApi(URL_API);
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    Scanner teclado = new Scanner(System.in);

    private List<DatosLibros> librosAPI;



    public void buscarLibro(){
        System.out.println("Ingrese el nombre del libro que desea buscar");
        String libroSolicitado = teclado.nextLine();


        Optional<DatosLibros> libroEncontrado = librosAPI.stream()
                .filter( l -> l.titulo().equalsIgnoreCase(libroSolicitado))
                .findFirst();

        if (libroEncontrado.isPresent()){
            DatosLibros libro = libroEncontrado.get();
            System.out.println(libro);
        }else{
            System.out.println("El titulo: " + libroSolicitado + ", no se encuentra.");
        }
    }

    //Obtiene los libros en la variable librosAPI
    public void obtenerLibros() {

        var json = consumoApi.obtenerDatos();

        //creado los records ahora puedo convertir el json a objeto java
        //necesito unos metodos de la libreria Jackson para hacer la conversion

        DatosApi datos =  convierteDatos.obtenerDatos(json, DatosApi.class);
        this.librosAPI = datos.libros();

        //mostrardatos
        //this.librosAPI.stream().forEach(System.out::println);
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
                    buscarLibro();
                    break;
                case 2:
                    //buscarEpisodioPorSerie();
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

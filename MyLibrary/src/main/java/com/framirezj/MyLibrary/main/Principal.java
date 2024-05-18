package com.framirezj.MyLibrary.main;


import com.framirezj.MyLibrary.api.ConsumoApi;
import com.framirezj.MyLibrary.record.DatosApi;
import com.framirezj.MyLibrary.record.DatosLibros;
import com.framirezj.MyLibrary.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    Scanner teclado = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();



    //METODO DE LA OPCION 1 PARA BUSCAR UN LIBRO
    public void buscarLibro(){
        System.out.println("Ingrese el nombre del libro que desea buscar");
        String libroSolicitado = teclado.nextLine();


        //llamada a la api por titulo
        String json = consumoApi.obtenerDatos(URL_BASE +"?search="+ libroSolicitado.replace(" ", "+"));
        DatosApi libroEncontrado = convierteDatos.obtenerDatos(json, DatosApi.class);

        Optional<DatosLibros> libro = libroEncontrado.libros().stream()
                .findFirst();

        if (libro.isPresent()){
            System.out.println(libro.get());
        }else{
            System.out.println("el libro no se encuentra.");
        }

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

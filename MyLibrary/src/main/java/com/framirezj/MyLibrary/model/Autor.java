package com.framirezj.MyLibrary.model;

import com.framirezj.MyLibrary.record.DatosAutor;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String FechaDeNacimiento;
    private String FechaDeMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    //constr

    public Autor() {}

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        FechaDeNacimiento = datosAutor.FechaDeNacimiento();
        FechaDeMuerte = datosAutor.FechaDeMuerte();

    }

    //GYS


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

    public String getFechaDeNacimiento() {
        return FechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        FechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeMuerte() {
        return FechaDeMuerte;
    }

    public void setFechaDeMuerte(String fechaDeMuerte) {
        FechaDeMuerte = fechaDeMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }



    @Override
    public String toString() {
        return
                "Autor: '" + nombre + '\'' +
                ", FechaDeNacimiento: '" + FechaDeNacimiento + '\'' +
                ", FechaDeMuerte: '" + FechaDeMuerte + '\'' +
                ", libros: " + libros.stream().map(Libro::getTitulo).collect(Collectors.toList()) +
                '}';
    }
}

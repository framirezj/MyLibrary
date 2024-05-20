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
    private String fechaDeNacimiento;
    private String fechaDeMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    //constructores

    public Autor() {}

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        fechaDeNacimiento = datosAutor.FechaDeNacimiento();
        fechaDeMuerte = datosAutor.FechaDeMuerte();

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
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(String fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
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
                Fecha de Nacimiento: %s
                Fecha de Muerte: %s
                Libros: %s
                """.formatted(this.nombre,this.fechaDeNacimiento,this.fechaDeMuerte,this.libros.stream().map(Libro::getTitulo).collect(Collectors.toList()));
    }
}

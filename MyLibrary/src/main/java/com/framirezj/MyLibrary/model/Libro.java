package com.framirezj.MyLibrary.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Double cantidadDeDescargas;


    //
    @ManyToOne
    private Autor autor;


    //
    @OneToMany(mappedBy = "libro")
    private List<Lenguaje> lenguaje;


    //GyS


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

    public Double getCantidadDeDescargas() {
        return cantidadDeDescargas;
    }

    public void setCantidadDeDescargas(Double cantidadDeDescargas) {
        this.cantidadDeDescargas = cantidadDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Lenguaje> getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(List<Lenguaje> lenguaje) {
        this.lenguaje = lenguaje;
    }
}
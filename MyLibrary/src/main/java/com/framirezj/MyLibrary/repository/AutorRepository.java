package com.framirezj.MyLibrary.repository;

import com.framirezj.MyLibrary.model.Autor;
import com.framirezj.MyLibrary.record.DatosAutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {


    Autor findByNombreContainsIgnoreCase(String nombre);


    //Buscar autores que estaban vivos en el año indicado.
    @Query("SELECT autor\n" +
            "FROM Autor autor\n" +
            "WHERE (autor.fechaDeNacimiento <= :fecha)\n" +
            "AND (autor.fechaDeMuerte >= :fecha OR autor.fechaDeMuerte IS NULL)\n")
    List<Autor> buscarAutorVivo(String fecha);




}
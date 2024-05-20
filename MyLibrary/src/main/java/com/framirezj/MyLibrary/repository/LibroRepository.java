package com.framirezj.MyLibrary.repository;

import com.framirezj.MyLibrary.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {


    @Query("SELECT libro.idioma as idioma, COUNT(*) as count FROM Libro libro GROUP BY libro.idioma")
    List<LibroIdiomaCount> buscarIdiomasCount();

}

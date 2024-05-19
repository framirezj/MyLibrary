package com.framirezj.MyLibrary.repository;

import com.framirezj.MyLibrary.model.Autor;
import com.framirezj.MyLibrary.record.DatosAutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {


    Autor findByNombreContainsIgnoreCase(String nombre);

}

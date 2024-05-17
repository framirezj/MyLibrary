package com.framirezj.MyLibrary.repository;

import com.framirezj.MyLibrary.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}

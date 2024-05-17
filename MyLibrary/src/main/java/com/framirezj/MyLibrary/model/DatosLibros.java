package com.framirezj.MyLibrary.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(

        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> author,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Double cantidadDeDescargas
) {
}

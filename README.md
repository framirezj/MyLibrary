# Proyecto de Aplicación de Consola - Librería de Libros.

![image](https://github.com/framirezj/MyLibrary/assets/12649259/70ebfb04-379d-494c-9a7c-7e353d38f124)


Este es un proyecto de aplicación de consola desarrollado con Java 17, Maven y Spring Boot, que utiliza PostgreSQL como base de datos y Spring Data JPA para interactuar con ella. La aplicación consume la API externa Gutendex, que proporciona información sobre una amplia variedad de libros.

## Funcionalidades

1. **Buscar Libros**: Esta función busca libros en la API Gutendex y los guarda en la base de datos PostgreSQL si se encuentran.
2. **Listar Libros Guardados**: Muestra una lista de los libros almacenados en la base de datos.
3. **Listar Autores de Libros**: Muestra una lista de los autores de los libros almacenados en la base de datos.
4. **Listar Autores Vivos en un Año Indicado**: Permite buscar y mostrar los autores que estuvieron vivos en un año específico.
5. **Listar Libros por Idioma Indicado**: Muestra los libros almacenados en la base de datos filtrados por el idioma indicado.
6. **Top 10 de Libros Más Descargados**: Presenta una lista de los 10 libros más descargados.

## Tecnologías Utilizadas

- JDK 17
- Maven
- Spring Boot
- PostgreSQL
- Spring Data JPA

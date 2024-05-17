package com.framirezj.MyLibrary.service;

public interface IConvierteDatos {

    default <T> T obtenerDatos(String json, Class<T> clase){
        return null;
    }

}

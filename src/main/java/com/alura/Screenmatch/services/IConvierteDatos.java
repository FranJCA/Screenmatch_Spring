package com.alura.Screenmatch.services;

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class <T> clase);


}

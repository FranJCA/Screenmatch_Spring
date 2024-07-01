package com.alura.Screenmatch.principal;

import com.alura.Screenmatch.model.DatosEpisodio;
import com.alura.Screenmatch.model.DatosSerie;
import com.alura.Screenmatch.model.DatosTemporadas;
import com.alura.Screenmatch.services.ConsumoAPI;
import com.alura.Screenmatch.services.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE="http://www.omdbapi.com/?t=";
    private final String API_KEY="&apikey=630989c3";

    private  ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElmenu(){
        System.out.println("Por favor escribe el nombre de la serie que deseas ver");
// Busca los datos generales de las series
        var nombreSerie = teclado.nextLine();
// Remplaza los espacios por un "+"
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);

// Imprime el JSON recibido para verificar su contenido
        System.out.println(json);

        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);  // Asegúrate de que el método toString esté bien implementado

// TODO: Busca los datos de todas las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <= datos.TotalDeTemporadas(); i++) {

            // Corrige la construcción de la URL para incluir el signo igual
            json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
//            System.out.println(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);

            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }

        temporadas.forEach(System.out::println);  // Asegúrate de que el método toString esté bien implementado
        /// mostrar solo esl tutiol de los episodios y la temporada
//        for (int i = 0; i < datos.TotalDeTemporadas(); i++) {
//            List<DatosEpisodio> episodiosTemporada =temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporada.size(); j++) {
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }
        //la funcion lambda simplifica el codigo anterior
 ///funciones landa en Java
        temporadas.forEach(t -> t.episodios().forEach(e-> System.out.println(e.titulo())));
        //



    }

}

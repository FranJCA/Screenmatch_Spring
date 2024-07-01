package com.alura.Screenmatch;

import com.alura.Screenmatch.model.DatosEpisodio;
import com.alura.Screenmatch.model.DatosSerie;
import com.alura.Screenmatch.model.DatosTemporadas;
import com.alura.Screenmatch.principal.Principal;
import com.alura.Screenmatch.services.ConsumoAPI;
import com.alura.Screenmatch.services.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
			//ya esta migrado
//		var consumoApi= new ConsumoAPI();
//		var json = consumoApi.obtenerDatos("http://www.omdbapi.com/?t=game+of+thrones&apikey=630989c3");
//		System.out.println(json);
////TODO add --------------
//		ConvierteDatos conversor = new ConvierteDatos();
//		var datos = conversor.obtenerDatos(json, DatosSerie.class);
//		System.out.println(datos);
//		json= consumoApi.obtenerDatos("http://www.omdbapi.com/?t=game+of+thrones&Season=1&episode=1&apikey=630989c3");
//		DatosEpisodio episodios = conversor.obtenerDatos(json, DatosEpisodio.class);
//		System.out.println(episodios);
		//fin de bloque
		Principal principal= new Principal();
		principal.muestraElmenu();


	}
}

package com.alura.Screenmatch.principal;

import com.alura.Screenmatch.model.DatosEpisodio;
import com.alura.Screenmatch.model.DatosSerie;
import com.alura.Screenmatch.model.DatosTemporadas;
import com.alura.Screenmatch.model.Episodio;
import com.alura.Screenmatch.services.ConsumoAPI;
import com.alura.Screenmatch.services.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
        //convertir todas las informaciones a una lista del tipo DatosEpisodio
        List <DatosEpisodio> datosEpisodios= temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                 .collect(Collectors.toList()); //LA DIFERENCIA CON este y el de  abajo es que este es una lista mutable
               // .toList(); //crea una lista imutable asi que si quiero agregar una nueva SERIO nos lo impediriAAAAAAAA!
        //TAP 5 EPISOPOS
        System.out.println("Top 5 episodios");
        datosEpisodios.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primer filtro (N/A)"+ e)) //peek de ver, hace una visualizacion del proceso detras del filtro qeu esta aplicando
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                .peek(e -> System.out.println("Segundo Ordenacion (M>m)"+ e))
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("Tercer Filtro Mayuscula"+ e))
                .limit(5)
                .forEach(System.out::println);

        //convirtiendo los datos a una lista de tipo clase episodio
        List <Episodio> episodios = temporadas.stream()
                .flatMap(t-> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(),d)) )
                .collect(Collectors.toList());
        episodios.forEach(System.out::println);
        // Busque de episodios  partir de X año
        System.out.println("POR favor indica el año apartir del cual deseas ver los episodios");
        var fecha = teclado.nextInt();
        teclado.nextLine();//esto es para los problemas que se dan cuando se da el enter

        LocalDate fechaBusqueda = LocalDate.of(fecha,1,1);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodios.stream()
                .filter(e -> e.getFechaDeLanzameinto() != null && e.getFechaDeLanzameinto().isAfter(fechaBusqueda))
                .forEach(e -> System.out.println(
                        "temporada " + e.getTemporada() +
                                " Episodio "+ e.getTitulo() +
                                "Fecha de lanzamiento " + e.getFechaDeLanzameinto().format(dtf)
                        )
                        );


        // Busca episodios por un pedazo de titulo
        System.out.println("Por favor escriba el titulo del episodio que desea ver");
        var pedazoTitulo= teclado.nextLine();
        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase() .contains(pedazoTitulo.toUpperCase()))
                .findFirst();

        if (episodioBuscado.isPresent()){
            System.out.println("Episodio encontrado");
            System.out.println("Los datos son "+ episodioBuscado.get());
        }else {
            System.out.println("Episodio no eoncotrado");
        }
        Map <Integer,Double> evaluacionesPorTemporada = episodios.stream()
                .filter(e ->e.getEvaluacion() >0.0 )
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getEvaluacion)
                        ));
        System.out.println(evaluacionesPorTemporada);

    DoubleSummaryStatistics est = episodios.stream()
            .filter(e ->e.getEvaluacion() >0.0 )
            .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));
        System.out.println(est);
        System.out.println("media de las evaluaciones:" + est.getAverage());
        System.out.println("Episodio Mejor evaluado: " + est.getMax());
        System.out.println("Episodio Peor evaluado"+ est.getMin());


    }

}

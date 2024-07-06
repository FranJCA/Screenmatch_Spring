package com.alura.Screenmatch.principal;

import java.util.Arrays;
import java.util.List;

public class EjemploStreams {

  public  void  muestraEjemplo(){
    List<String> nombres = Arrays.asList("brenda","luis","Maria fernanda","Eric","genesys");
    //esto es lo mismo
   // nombres.stream().sorted().limit(4).filter(n->n.startsWith("B")).map(n->n.toUpperCase()).forEach(System.out::println);
    //que esto:
    nombres.stream()
            .sorted()
            .limit(4)
            .filter(n->n.startsWith("B"))
            .map(n->n.toUpperCase())
            .forEach(System.out::println);
    //se deja entrerenglon para mejorar la lectura, YY el orden de los operadores afecta
    // a , no es lo mismo tener el limit antes del map que despues de este

  }



}

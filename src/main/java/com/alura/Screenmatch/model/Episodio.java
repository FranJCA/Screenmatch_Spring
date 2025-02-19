package com.alura.Screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    // propiedades privadas
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double evaluacion;
    private LocalDate fechaDeLanzameinto;

    public Episodio(Integer numero, DatosEpisodio d) {
        this.temporada= numero;
        this.titulo = d.titulo();
        this.numeroEpisodio = d.numeroEpisodio();
        try{
            this.evaluacion =  Double.valueOf(d.evaluacion());
        }
        catch(NumberFormatException e){
            this.evaluacion = 0.0;
        }
        try{
            this.fechaDeLanzameinto = LocalDate.parse(d.fechaDeLanzamiento());
        }
        catch(DateTimeParseException e){
            this.fechaDeLanzameinto = null ;
        }






    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public LocalDate getFechaDeLanzameinto() {
        return fechaDeLanzameinto;
    }

    public void setFechaDeLanzameinto(LocalDate fechaDeLanzameinto) {
        this.fechaDeLanzameinto = fechaDeLanzameinto;
    }

    @Override
    public String toString() {
        return
                "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", evaluacion=" + evaluacion +
                ", fechaDeLanzameinto=" + fechaDeLanzameinto
                 ;
    }
}

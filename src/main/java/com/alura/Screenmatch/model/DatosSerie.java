package com.alura.Screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSerie(
        @JsonAlias("Title") String titulo,
        @JsonAlias("totalSeasons") Integer TotalDeTemporadas,
        @JsonAlias("imdbRatring") String evaluacion



) {
}

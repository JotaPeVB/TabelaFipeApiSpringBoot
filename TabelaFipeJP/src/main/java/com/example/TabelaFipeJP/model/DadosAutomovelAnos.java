package com.example.TabelaFipeJP.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutomovelAnos(@JsonAlias("Marca") String marcaCarro,
                                 @JsonAlias("Modelo") String modeloCarro,
                                 @JsonAlias("AnoModelo") Integer anoCarro,
                                 @JsonAlias("Combustivel") String tipoCombustivel,
                                 @JsonAlias("Valor") String valor) {
}

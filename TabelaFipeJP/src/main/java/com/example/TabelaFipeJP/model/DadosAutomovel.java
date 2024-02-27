package com.example.TabelaFipeJP.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosAutomovel(@JsonAlias("codigo") String codigo,
                             @JsonAlias("nome") String nome) {
}

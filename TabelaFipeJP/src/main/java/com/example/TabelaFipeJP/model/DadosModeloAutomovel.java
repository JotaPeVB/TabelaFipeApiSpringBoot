package com.example.TabelaFipeJP.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModeloAutomovel (List<DadosAutomovel> modelos) {
}

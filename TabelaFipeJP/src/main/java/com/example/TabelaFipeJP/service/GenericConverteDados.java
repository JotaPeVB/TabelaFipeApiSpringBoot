package com.example.TabelaFipeJP.service;

import java.util.List;

public interface GenericConverteDados {
    <T> T obterDados(String json, Class<T> classe);
    <T> List<T> obterLista(String json, Class<T> classe);
}

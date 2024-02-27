package com.example.TabelaFipeJP.principal;

import com.example.TabelaFipeJP.model.DadosAutomovel;
import com.example.TabelaFipeJP.model.DadosAutomovelAnos;
import com.example.TabelaFipeJP.model.DadosModeloAutomovel;
import com.example.TabelaFipeJP.service.ConsumirApi;
import com.example.TabelaFipeJP.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    final private String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
    final private String MARCAS = "/marcas";
    final private String MODELOS = "/modelos";
    final private String ANOS = "/anos";
    private String codigoMarca, codigoModelo, codigoAno, codigoNomeAutomovel;
    private ConsumirApi consumirApi = new ConsumirApi();
    private ConverteDados obterDados = new ConverteDados();
    private Scanner leitura = new Scanner(System.in);
    private List<DadosAutomovelAnos> listaAutomoveis = new ArrayList<>();
    private List<DadosAutomovel> listaDeModelos = new ArrayList<>();
    public void exibeMenu() {
        System.out.print("Escolha entre\n Carros, Motos ou Caminhoes\n Digite sua resposta:\n");
        var tipoVeiculo = leitura.nextLine();

        var json = consumirApi.consumirApi(ENDERECO + tipoVeiculo.toLowerCase() + MARCAS);
        List<DadosAutomovel> dadosAutomovel = obterDados.obterLista(json, DadosAutomovel.class);

        dadosAutomovel.stream().sorted(Comparator.comparing(DadosAutomovel::nome))
                .forEach(e -> System.out.println("Nome da Marca: (" +
                        e.nome() + ") Codigo da Marca: " + e.codigo()));

        System.out.println("Escolha um dos códigos na tela pra selecionar a marca do carro:");
        codigoMarca = leitura.nextLine();

        json = consumirApi.consumirApi(ENDERECO + tipoVeiculo.toLowerCase() + MARCAS +
            "/" + codigoMarca + MODELOS);
        DadosModeloAutomovel dadosModeloAutomovel = obterDados.obterDados(json, DadosModeloAutomovel.class);

        dadosModeloAutomovel.modelos().stream()
                .sorted(Comparator.comparing(DadosAutomovel::codigo))
                    .forEach(e -> System.out.println("Nome do modelo: (" +
                    e.nome() + ") Codigo do modelo: " + e.codigo()));

        System.out.println("Escreva o nome de um modelo pra buscar: ");
        var nomeCarro = leitura.nextLine();

        listaDeModelos = dadosModeloAutomovel.modelos().stream()
                .sorted(Comparator.comparing(DadosAutomovel::codigo))
                        .filter(e -> e.nome().toLowerCase().contains(nomeCarro.toLowerCase()))
                                .collect(Collectors.toList());


        listaDeModelos.forEach(e -> System.out.println("Nome do modelo: (" +
                e.nome() + ")Codigo do modelo: " + e.codigo()));
        System.out.println("Escolha um dos códigos na tela pra selecionar o modelo do carro:");
        codigoNomeAutomovel = leitura.nextLine();

        json = consumirApi.consumirApi(ENDERECO + tipoVeiculo.toLowerCase() + MARCAS +
                "/" + codigoMarca + MODELOS + "/" + codigoNomeAutomovel + ANOS);
        List<DadosAutomovel> dadosNomeAutomovel = obterDados.obterLista(json, DadosAutomovel.class);

        List<String> listaCodigosAnos = dadosAutomovel.stream()
                .map(e -> e.codigo())
                    .collect(Collectors.toList());

//        dadosNomeAutomovel.forEach(e -> System.out.println("Ano e tipo de combustível: (" +
//                e.tipoDeCombustivelAno() + ")Codigo: " + e.codigo()));

        for (String codigoAno: listaCodigosAnos) {
            json = consumirApi.consumirApi(ENDERECO + tipoVeiculo.toLowerCase() + MARCAS +
                    "/" + codigoMarca + MODELOS + "/" + codigoNomeAutomovel + ANOS + "/" + codigoAno);
            DadosAutomovelAnos dadosAutomovelAnos = obterDados.obterDados(json, DadosAutomovelAnos.class);
            listaAutomoveis.add(dadosAutomovelAnos);
        }

        listaAutomoveis.stream()
                .sorted(Comparator.comparing(DadosAutomovelAnos::anoCarro))
                .forEach(t -> System.out.println("Marca: " + t.marcaCarro() + "\n" +
                        "Nome automovel: " + t.modeloCarro() + "\n" +
                        "Ano do automovel: " + t.anoCarro() + "\n" +
                        "Valor: " + t.valor() + "\n" +
                        "Tipo de combustivel: " + t.tipoCombustivel() + "\n" +
                        "______________________________________________________//"));
    }
}

package br.com.alura.cinema.principal;

import br.com.alura.cinema.model.DadosEpisodio;
import br.com.alura.cinema.model.DadosSerie;
import br.com.alura.cinema.model.DadosTemporada;
import br.com.alura.cinema.service.ConsumoApi;
import br.com.alura.cinema.service.ConverterDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitor = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=b76cfa9";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverterDados conversor = new ConverterDados();


    public void exibirMenu () {
        System.out.println("Digite uma série para busca: ");
        var nomeSerie = leitor.nextLine();
        var json = consumo.obterDadosApi(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

		for(int i = 1; i<=dados.totalTemporadas(); i++) {
			json = consumo.obterDadosApi(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);

//        for (int i = 0; i < dados.totalTemporadas(); i++){
//            List<DadosEpisodio> listaEpisodios = temporadas.get(i).episodios();
//            for (int j = 0; j < listaEpisodios.size(); j++){
//                System.out.println(listaEpisodios.get(j).titulo());
//            }
//        }
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}

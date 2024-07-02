package br.com.alura.cinema.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}

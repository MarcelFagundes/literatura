package com.alura.literatura;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class BookApiService {

    private final HttpClient httpClient;

    // Injeção do HttpClient via construtor
    public BookApiService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String fetchBooks() {
        try {
            // Criando a requisição
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://gutendex.com/books/"))
                    .GET()
                    .header("Accept", "application/json")
                    .build();

            // Enviando a requisição e capturando a resposta
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new RuntimeException("Erro ao buscar livros: " + response.statusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Falha ao realizar a requisição", e);
        }
    }
}


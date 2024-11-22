package com.alura.literatura;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class BookApiService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    // Injeção do HttpClient via construtor
    public BookApiService(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.objectMapper = new ObjectMapper();
    }

    public Book fetchBooks() {
        try {
            // Requisição HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://gutendex.com/books/"))
                    .GET()
                    .header("Accept", "application/json")
                    .build();

            // Enviando a requisição e capturando a resposta
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Mapeando JSON para o objeto Book
                return objectMapper.readValue(response.body(), Book.class);
            } else {
                throw new RuntimeException("Erro ao buscar livros: " + response.statusCode());
            }
        } catch (Exception e) {
            //throw new RuntimeException("Falha ao realizar a requisição", e);
            throw new RuntimeException("Falha ao processar a resposta JSON", e);
        }
    }
}


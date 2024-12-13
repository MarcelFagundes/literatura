package com.challengeliteratura.challengeliteratura.client;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import com.challengeliteratura.challengeliteratura.entity.AuthorEntity;
import com.challengeliteratura.challengeliteratura.entity.BookEntity;
import com.challengeliteratura.challengeliteratura.mapper.ConvertData;
import com.challengeliteratura.challengeliteratura.model.Book;
import com.challengeliteratura.challengeliteratura.model.Results;
import com.challengeliteratura.challengeliteratura.repository.AuthorRepository;
import com.challengeliteratura.challengeliteratura.repository.BookRepository;
import com.challengeliteratura.challengeliteratura.service.BookApiService;

public class ClientLiterature {

    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private final Scanner inData = new Scanner(System.in);
    private final BookApiService consumoApi = new BookApiService();
    private final ConvertData conversor = new ConvertData();
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final int currentYear = Year.now().getValue();
    private final LocalDate currentDate = LocalDate.now();

    public ClientLiterature(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void menu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    \nEscolha o número da opção desejada:\n
                    	1) - Buscar livro por título na API
                    	2) - Listar livros registrados
                    	3) - Listar autores registrados
                    	4) - Listar autores vivos em um determinado ano
                    	5) - Listar livros por idioma
                    	6) - Listar TOP 10 livros por downloads
                    	7) - Buscar autor por livro na API
                    	8) - Gerar estatíticas de livros
                    	0) - Sair
                    """;

            System.out.println(menu);
            option = inData.nextInt();
            inData.nextLine();

            switch (option) {
                case 1:
                    try {
                        findBookAPI();
                    } catch (IllegalArgumentException e) {
                        System.out.println("[ERRO]" + e.getMessage());
                    }
                    break;
                case 2:
                    findBook();
                    break;
                case 3:
                    findAuthors();
                    break;
                case 4:
                    findAuthorsNoDeath();
                    break;
                case 5:
                    findLanguages();
                    break;
                case 6:
                    listTop10ByDownloads();
                    break;
                case 7:
                    try {
                        findAuthorAPI();
                    } catch (IllegalArgumentException e) {
                        System.out.println("[ERRO]" + e.getMessage());
                    }
                case 8:
                    generateBookDownloadStatistics();
                    break;
                case 0:
                    System.out.println("Saindo da aplicação\n");
                    break;
                default:
                    System.out.println("[ERRO] Opção inválida\n");
            }
        }
        inData.close();
        System.exit(0);
    }

    private void findAuthors() {
        List<AuthorEntity> authors = authorRepository.findAll();
        int currentYear = currentDate.getYear();

        if (!authors.isEmpty()) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println(String.format("%-30s", "AUTORES REGISTRADOS").toUpperCase());
            System.out.println("=".repeat(50));
            for (AuthorEntity author : authors) {
                System.out.println("Nome          : " + author.getName());
                System.out.println("Nascimento    : " +
                        (author.getBirthYear() != null ? author.getBirthYear() : "[INFO] Data de nascimento não registrada"));
                System.out.println("Morte         : " +
                        (author.getDeathYear() != null && author.getDeathYear() <= currentYear
                                ? author.getDeathYear()
                                : "Autor ainda vivo"));
                System.out.println("Livros        : " +
                        (author.getBooks() != null ? author.getBooks().getTitle() : "[INFO] Nenhum livro registrado"));
                System.out.println("-".repeat(50));
            }
        } else {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("[INFO] Nenhum autor encontrado.".toUpperCase());
            System.out.println("=".repeat(50));
        }
    }

    private void findBook() {
        List<BookEntity> books = bookRepository.findAll();

        if (!books.isEmpty()) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println(String.format("%-30s", "LIVROS REGISTRADOS").toUpperCase());
            System.out.println("=".repeat(50));
            for (BookEntity book : books) {

                System.out.println("Titulo: " + book.getTitle());
                System.out.println("Autor: " +
                        (book.getAuthor() != null ? book.getAuthor().getName() : "[INFO] Author inexistente ou não registrado"));
                System.out.println("Data de Nascimento do autor: " +
                        (book.getAuthor().getBirthYear() != null ? book.getAuthor().getBirthYear() : "[INFO] Data de nascimento não informada"));

                if ((book.getAuthor().getBirthYear() == null) || book.getAuthor().getDeathYear() == null) {
                    System.out.println("[INFO] Data de Nascimento ou de Morte do autor não informadas");
                } else if (book.getAuthor().getDeathYear() > currentYear) {
                    int age = (currentYear - book.getAuthor().getBirthYear());
                    System.out.println("[INFO] O autor está vivo. Ano de nascimento: " + book.getAuthor().getBirthYear() + ". Idade atual: " + age + " anos.");
                } else {
                    int ageAtDeath = (book.getAuthor().getDeathYear() - book.getAuthor().getBirthYear() - currentYear);
                    System.out.println("[INFO] O autor está falecido. Ano de nascimento: " + book.getAuthor().getBirthYear() + ", Ano de morte: " + book.getAuthor().getDeathYear());
                }
                System.out.println("Idioma: " + book.getLanguage());
                System.out.println("Download: " + book.getDownload());
                System.out.println("=".repeat(50));
            }

        } else {
            System.out.println("\n\n [INFO] Nenhum livro encontrado. \n\n");
        }
    }

    private void findAuthorsNoDeath() {

        System.out.println("Informe o ano que você deseja buscar: ");
        var year = inData.nextInt();
        inData.nextLine();

        List<AuthorEntity> authors = authorRepository.findForYear(year);

        if (!authors.isEmpty()) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println(String.format("%-30s", "Listar autores vivos em um determinado ano:").toUpperCase());
            System.out.println("=".repeat(50));
            for (AuthorEntity author : authors) {
                System.out.println("Nome: " + author.getName());
                if ((author.getBirthYear() == null) || author.getDeathYear() == null) {
                    System.out.println("[INFO] Data de Nascimento ou de Morte do autor não informadas");
                } else if (author.getDeathYear() > currentYear) {
                    int age = (currentYear - author.getBirthYear());
                    System.out.println("O autor está vivo. Ano de nascimento: " + author.getBirthYear() + ". Idade atual: " + age + " anos.");
                } else {
                    //int ageAtDeath = (currentYear - author.getDeathYear());
                    System.out.println("O autor está falecido. Ano de nascimento: " + author.getBirthYear() + ", Ano de morte: " + author.getDeathYear());
                }
                System.out.println("Livros: " + author.getBooks().getTitle());
                System.out.println("=".repeat(50));
            }
        } else {
            System.out.println("[INFO] Busca não encontrada");
        }
    }

    private void findLanguages() {
        var menu = """
                Selecione um Idioma:
                    1) - Português
                    2) - Inglês
                    3) - Espanhol
                    4) - Francês
                """;

        System.out.println(menu);
        int language;
        String selection = "";

        while (true) {
            System.out.print("Escolha uma opção válida 1 a 4: ");
            if (inData.hasNextInt()) {
                language = inData.nextInt();
                inData.nextLine();

                switch (language) {
                    case 1 -> selection = "pt";
                    case 2 -> selection = "en";
                    case 3 -> selection = "es";
                    case 4 -> selection = "fr";
                    default -> {
                        System.out.println("[ERRO] Opção inválida. Tente novamente.");
                        continue;
                    }
                }
                break;
            } else {
                System.out.println("[ERRO] Opção inválida. Digite um número.");
                inData.nextLine();
            }
        }

        System.out.println("Idioma selecionado: " + selection);

        List<BookEntity> books = bookRepository.findForLanguage(selection);

        if (!books.isEmpty()) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println(String.format("%-30s", "LIVROS POR IDIOMA").toUpperCase());
            System.out.println("=".repeat(50));

            for (BookEntity book : books) {
                System.out.println("Titulo: " + book.getTitle());
                System.out.println("Autor: " + book.getAuthor().getName());
                System.out.println("Idioma: " + book.getLanguage());
                System.out.println("Download: " + book.getDownload());
                System.out.println("=".repeat(50));
            }

        } else {
            System.out.println("[INFO] Busca não encontrada");
        }
    }

    private Results getDataAPI() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(String.format("%-30s", "Digite o nome do livro que você deseja buscar ").toUpperCase());
        System.out.println("=".repeat(50)+"\n");
        var title = inData.nextLine();
        title = title.replace(" ", "%20");
        System.out.println("\n" + "=".repeat(50));
        System.out.println(String.format("%-30s", "Pesquisando por titulo : "+title).toUpperCase());
        System.out.println("\n"+URL_BASE + title);
        System.out.println("=".repeat(50));
        var json = consumoApi.dataBookApi(URL_BASE + title);
        //System.out.println(json);
        return conversor.getInformationAPI(json, Results.class);
    }

    private void findBookAPI() {
        Results booksApi = getDataAPI();

        if (!booksApi.results().isEmpty()) {

            BookEntity book = new BookEntity(booksApi.results().get(0));
            System.out.println("\n" + "=".repeat(50));
            System.out.println(String.format("%-30s", "LIVRO ENCONTRADO").toUpperCase());
            System.out.println(book.getTitle());
            System.out.println("=".repeat(50));

            if (book.getAuthor() == null || book.getAuthor().getName().isBlank()) {
                AuthorEntity placeholderAuthor = new AuthorEntity();
                placeholderAuthor.setName("[INFO] Autor inexistente ou não cadastrado");
                book.setAuthor(placeholderAuthor);
            }

            boolean exists = bookRepository.findByTitle(book.getTitle()).isPresent();
            if (exists) {
                throw new IllegalArgumentException("O livro consultado já foi inserido no banco de dados.");
            }

            bookRepository.save(book);
        }
        System.out.println("Buscar livro por título API: ");
        System.out.println(booksApi);
    }

    public void findAuthorAPI() {
        System.out.println("Digite o nome do autor que você deseja buscar: ");
        String authorName = inData.nextLine();

        List<AuthorEntity> authorsInDb = authorRepository.findAuthorByName(authorName);
        System.out.println(authorsInDb.isEmpty());

        if (!authorsInDb.isEmpty()) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println(String.format("%-30s", "Autores encontrados no banco de dados").toUpperCase());
            System.out.println(String.format("%-30s", "Buscando Autores na API:").toUpperCase());
            System.out.println("=".repeat(50));

            for (AuthorEntity author : authorsInDb) {
                System.out.println("Nome: " + author.getName());
                System.out.println("Nome: " + author.getBooks().getTitle());
                System.out.println("=".repeat(50));
            }
        } else {
            System.out.println("\n" + "=".repeat(50));
            System.out.println(String.format("%-30s", "Autores não encontrados no banco de dados").toUpperCase());
            System.out.println(String.format("%-30s", "Buscando Autores na API:").toUpperCase());
            System.out.println("=".repeat(50));
            Results authorsApi = getDataAPIByAuthor(authorName);

            if (!authorsApi.results().isEmpty() || authorsApi.results() == null) {
                System.out.println("\n" + "=".repeat(50));
                System.out.println(String.format("%-30s", "Autores encontrados na API:").toUpperCase());
                System.out.println("=".repeat(50));
                for (Book book : authorsApi.results()) {
                    System.out.println("Nome do Autor: " + book.authors().get(0).name());
                    System.out.println("Título do Livro: " + book.title());
                    System.out.println("=".repeat(50));

                    BookEntity bookEntity = new BookEntity(book);
                    boolean exists = bookRepository.findByTitle(book.title()).isPresent();
                    if (exists) {
                        throw new IllegalArgumentException("[INFO] O autor consultado já foi inserido no banco de dados.");
                    }
                    bookRepository.save(bookEntity);
                }

            } else {
                System.out.println("[INFO] Nenhum autor encontrado na API ou no banco de dados.");
            }
        }
    }

    private Results getDataAPIByAuthor(String authorName) {
        authorName = authorName.replace(" ", "%20");
        String formattedName = authorName.replace(" ", "%20");
        String url = URL_BASE + formattedName;
        System.out.println("Buscando por autor: " + authorName);
        System.out.println("URL: " + url);
        var json = consumoApi.dataBookApi(url);
        return conversor.getInformationAPI(json, Results.class);
    }

        public void listTop10ByDownloads() {
        List<BookEntity> topBooks = bookRepository.findTop10ByDownloadsDesc();
        System.out.println("\n" + "=".repeat(50));
        System.out.println(String.format("%-30s", "LISTAR TOP 10 LVROS POR DOWNLOADS").toUpperCase());
        System.out.println("=".repeat(50));

        if (topBooks.isEmpty()) {
            System.out.println("[INFO] Não há livros cadastrados no banco de dados.");
            return;
        }

        int rank = 1;
        for (BookEntity book : topBooks) {
            System.out.printf("%d° - %s -> Downloads: %d%n", rank++, book.getTitle(), book.getDownload());
        }
        System.out.println("=".repeat(50));
    }

    public void generateBookDownloadStatistics() {
        List<BookEntity> books = bookRepository.findAll();


        if (books.isEmpty()) {
            System.out.println("[INFO] Não há livros cadastrados no banco de dados.");
            return;
        }

        DoubleSummaryStatistics stats = books.stream()
                //.mapToDouble (BookEntity::getDownload) nullpointer
                .mapToDouble(book -> book.getDownload() != null ? book.getDownload() : 0)
                .summaryStatistics();

        System.out.println("\n" + "=".repeat(50));
        System.out.println("ESTATÍSTICAS DE DOWNLOADS");
        System.out.println("=".repeat(50));
        System.out.printf("Total de Downloads: %.0f%n", stats.getSum());
        System.out.printf("Média de Downloads: %.2f%n", stats.getAverage());
        System.out.printf("Máximo de Downloads: %.0f%n", stats.getMax());
        System.out.printf("Mínimo de Downloads: %.0f%n", stats.getMin());
        System.out.printf("Total de Livros: %d%n", stats.getCount());
        System.out.println("=".repeat(50));
    }
}
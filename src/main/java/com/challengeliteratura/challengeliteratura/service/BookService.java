//import com.challengeliteratura.challengeliteratura.entity.BookEntity;
//import com.challengeliteratura.challengeliteratura.model.Book;
//import com.challengeliteratura.challengeliteratura.repository.AuthorRepository;
//import com.challengeliteratura.challengeliteratura.repository.BookRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//@Service
//public class BookService {
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @Autowired
//    private AuthorRepository authorRepository;
//
//    @Transactional
//    public void saveBook(Book dataBook) {
//
//        Optional<BookEntity> livroExistente = bookRepository.findByTitle(dataBook.title());
//
//        if (livroExistente.isPresent()) {
//            System.out.println("O livro '" + dataBook.title() + "' já está registrado no banco de dados.");
//            return;
//        }
//
////        Book book = new Book();
////        book.setTitulo(dadosLivro.título());
////        book.setNumeroDownloads(dadosLivro.numeroDownloads());
////
////
////        livro.setIdioma(dadosLivro.idioma().isEmpty() ? null : dadosLivro.idioma().get(0));
////
////
////        List<Autor> autores = dadosLivro.autor().stream().map(dadosAutor -> {
////            return autorRepository.findByNome(dadosAutor.nome())
////                    .orElseGet(() -> {
////                        Autor novoAutor = new Autor();
////                        novoAutor.setNome(dadosAutor.nome());
////                        novoAutor.setAnoNascimento(dadosAutor.anoNascimento());
////                        novoAutor.setAnoFalecimento(dadosAutor.anoFalecimento());
////                        return novoAutor;
////                    });
////        }).collect(Collectors.toList());
////
////
////        autorRepository.saveAll(autores.stream().filter(autor -> autor.getId() == null).collect(Collectors.toList()));
////        livro.setAutores(autores);
////
////
////        livroRepository.save(livro);
////    }
////
////
////    @Transactional(readOnly = true)
////    public List<String> listarLivros() {
////        List<Livro> livros = livroRepository.findAll();
////
////
////        for (Livro livro : livros) {
////            livro.getAutores().size();
////        }
////
////
////        return livros.stream().map(livro -> {
////            String autores = livro.getAutores().stream()
////                    .map(Autor::getNome)
////                    .collect(Collectors.joining(", "));
////
////            return "-------------- Livro ----------------\n" +
////                    "Título: " + livro.getTitulo() + "\n" +
////                    "Autores: " + autores + "\n" +
////                    "Idioma: " + livro.getIdioma() + "\n" +
////                    "Número de Downloads: " + livro.getNumeroDownloads() + "\n" +
////                    "--------------------------------------";
////        }).collect(Collectors.toList());
////    }
////
////
////    public Optional<Livro> buscarPorTitulo(String titulo) {
////        return livroRepository.findByTitulo(titulo);
////    }
////
////
////    @Transactional(readOnly = true)
////    public List<Livro> buscarLivrosPorIdioma(String idioma) {
////        List<Livro> livros = livroRepository.findByIdiomaWithAutores(idioma);
////
////
////        return livros;
//    }
//}
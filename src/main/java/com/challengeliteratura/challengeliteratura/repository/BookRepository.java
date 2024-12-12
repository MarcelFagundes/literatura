package com.challengeliteratura.challengeliteratura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.challengeliteratura.challengeliteratura.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    @Query("SELECT l FROM BookEntity l WHERE l.language >= :idioma")
    List<BookEntity> findForLanguage(String idioma);

    Optional<Object> findByTitle(String title);

    @Query("SELECT b FROM BookEntity b ORDER BY b.download DESC")
    List<BookEntity> findTop10ByDownloadsDesc();

//    List<BookEntity> findByAuthor(String name);
}
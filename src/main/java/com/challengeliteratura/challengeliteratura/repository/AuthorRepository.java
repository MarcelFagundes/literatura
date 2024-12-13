package com.challengeliteratura.challengeliteratura.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.challengeliteratura.challengeliteratura.entity.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    @Query("SELECT a FROM AuthorEntity a WHERE :year between a.birthYear AND a.deathYear")
    List<AuthorEntity> findForYear(int year);

    List<AuthorEntity> findAuthorByName(String authorName);
}
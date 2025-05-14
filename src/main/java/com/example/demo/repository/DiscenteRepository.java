package com.example.demo.repository;

import com.example.demo.entity.Discente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DiscenteRepository extends JpaRepository<Discente, Long> {

    @Query("select a from Discente a where a.voto = ?1")
    List<Discente> findByGrade(int voto);

    @Query("select a from Discente a where a.voto >= 18")
    List<Discente> findPassedStudent();

    @Query("select a from Discente a where a.nome = ?1")
    List<Discente> findByName(String nome);

    @Query("select a from Discente a where a.cognome =?1")
    List<Discente> findByLastName(String cognome);

    @Query("select a from Discente a where a.nome ILIKE %:keyword% or a.cognome ILIKE %:keyword%")
    List<Discente> findByNameorLastName(@Param("keyword") String keyword );

    @Query("select a from Discente a where a.id = :id")
    Optional<Discente> findById(@Param("id") Long id);

    @Query("select a from Discente a where a.cittaDiResidenza ILIKE %:citta%")
    List<Discente> findByCity(@Param("citta") String citta);

}

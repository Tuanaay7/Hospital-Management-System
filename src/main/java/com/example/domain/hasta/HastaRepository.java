package com.example.domain.hasta;

import com.example.domain.personel.Personel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HastaRepository extends JpaRepository<Hasta, Long> {

    Optional<Hasta> findByTcNo(String tcNo);

    List<Hasta> findByPersonel(Personel personel);

    @Query("select count(h) from Hasta h where h.personel = :personel")
    Long countByPersonelExplicit(@Param("personel") Personel personel);
}

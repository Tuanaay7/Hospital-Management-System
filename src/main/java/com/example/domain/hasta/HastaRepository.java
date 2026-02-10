package com.example.domain.hasta;

import com.example.domain.personel.Personel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface HastaRepository extends JpaRepository<Hasta, Long> {

    Optional<Hasta> findByTcNo(String tcNo);

    List<Hasta> findByPersonel(Personel personel);
}

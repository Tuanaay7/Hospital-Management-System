package com.example.domain.personel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonelRepository extends JpaRepository<Personel, Long> {

    Optional<Personel> findByTcNo(String tcNo);

}

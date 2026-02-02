package com.example.domain.hasta;

import com.example.domain.personel.Personel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HastaService {

    private final HastaRepository repository;

    public HastaService(HastaRepository repository) {
        this.repository = repository;
    }

    public List<Hasta> findAll() {
        return repository.findAll();
    }

    public Optional<Hasta> findByTc(String tc) {
        return repository.findByTcNo(tc);
    }

    public List<Hasta> findByPersonel(Personel personel) {
        return repository.findByPersonel(personel);
    }

    public Hasta save(Hasta hasta) {
        return repository.save(hasta);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

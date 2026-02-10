package com.example.domain.personel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonelService {

    private final PersonelRepository repository;

    public PersonelService(PersonelRepository repository) {
        this.repository = repository;
    }

    public List<Personel> findAll() {
        return repository.findAll();
    }

    public Optional<Personel> findByTcNo(String tcNo) {
        return repository.findByTcNo(tcNo);
    }

    public Personel save(Personel personel) {
        return repository.save(personel);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

package com.example.domain.personel;

import com.example.domain.hasta.HastaRepository;
import com.example.exception.DuplicateTcException;
import com.example.exception.PersonelHasHastaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonelService {

    private final PersonelRepository repository;
    private final HastaRepository hastaRepository;

    public PersonelService(PersonelRepository repository,
                           HastaRepository hastaRepository) {
        this.repository = repository;
        this.hastaRepository = hastaRepository;
    }

    public List<Personel> findAll() {
        return repository.findAll();
    }

    public Optional<Personel> findByTcNo(String tcNo) {
        return repository.findByTcNo(tcNo);
    }

    public Personel save(Personel personel) {

        if (repository.findByTcNo(personel.getTcNo()).isPresent()) {
            throw new DuplicateTcException("Bu TC zaten kayıtlı");
        }

        return repository.save(personel);
    }

    public void deleteById(Long id) {

        Personel personel = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personel bulunamadı"));

        if (!hastaRepository.findByPersonel(personel).isEmpty()) {
            throw new PersonelHasHastaException(
                    "Bu personele bağlı hasta bulunmaktadır. Silinemez."
            );
        }

        repository.deleteById(id);
    }
}

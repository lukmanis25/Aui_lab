package com.example.demo.firma.service;

import com.example.demo.firma.entity.Pracownik;
import com.example.demo.firma.entity.Szef;
import com.example.demo.firma.repository.PracownikRepository;
import com.example.demo.firma.repository.SzefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PracownikService {

    /**
     * Repository for profession entity.
     */
    private PracownikRepository repository;
    private SzefRepository szefRepository;

    /**
     * @param repository repository for profession entity
     */
    @Autowired
    public PracownikService(PracownikRepository repository, SzefRepository szef_repository) {
        this.szefRepository = szef_repository;
        this.repository = repository;
    }

    @Transactional
    public Pracownik save(Pracownik pracownik) {
        return repository.save(pracownik);
    }

    public List<Pracownik> findAll(){ return repository.findAll();}
    public Optional<Pracownik> find(int id) {return repository.findById(id);}
    @Transactional
    public void delete(Pracownik pracownik) { repository.delete(pracownik);}
    public List<Pracownik> findAll(Szef szef) {
        return repository.findAllBySzef(szef);
    }


    public Optional<Pracownik> find(int szef_id, int pracownik_id) {
        Optional<Szef> szef = szefRepository.findById(szef_id);
        if (szef.isPresent()) {
            return repository.findByIdAndSzef(pracownik_id, szef.get());
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public void update(Pracownik pracownik) {
        repository.save(pracownik);
    }

}

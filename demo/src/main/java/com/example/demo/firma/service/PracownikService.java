package com.example.demo.firma.service;

import com.example.demo.firma.entity.Pracownik;
import com.example.demo.firma.repository.PracownikRepository;
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

    /**
     * @param repository repository for profession entity
     */
    @Autowired
    public PracownikService(PracownikRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(Pracownik pracownik) {
        repository.save(pracownik);
    }



    public List<Pracownik> findAll(){ return repository.findAll();}
    public Optional<Pracownik> findById(long id) {return repository.findById(id);}

}

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
public class SzefService {

    /**
     * Repository for profession entity.
     */
    private SzefRepository repository;

    /**
     * @param repository repository for profession entity
     */
    @Autowired
    public SzefService(SzefRepository repository) {
        this.repository = repository;
    }
    /**
     * Repository for profession entity.
     */
    @Transactional
    public void save(Szef szef) {
        repository.save(szef);
    }
    public List<Szef> findAll(){ return repository.findAll();}
    public Optional<Szef> find(int id) {return repository.findById(id);}
    public void delete(Szef szef) { repository.delete(szef);}

}
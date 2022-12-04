package com.example.demo.firma.service;

import com.example.demo.firma.entity.Szef;
import com.example.demo.firma.event.repository.SzefEventRepository;
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
    private SzefEventRepository eventRepository;

    /**
     * @param repository repository for profession entity
     */
    @Autowired
    public SzefService(SzefRepository repository, SzefEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }
    /**
     * Repository for profession entity.
     */
    @Transactional
    public void save(Szef szef) {
        repository.save(szef);
        eventRepository.create(szef);
    }
    public List<Szef> findAll(){ return repository.findAll();}
    public Optional<Szef> find(int id) {return repository.findById(id);}
    @Transactional
    public void delete(Szef szef) {
        eventRepository.delete(szef);
        repository.delete(szef);
    }
    @Transactional
    public void update(Szef szef) {
        repository.save(szef);
        eventRepository.create(szef);
    }
}
package com.example.demo.firma.repository;

import com.example.demo.firma.entity.Szef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface SzefRepository extends JpaRepository<Szef, String> {


    public Optional<Szef> findById(int id);

    public List<Szef> findAll();


}
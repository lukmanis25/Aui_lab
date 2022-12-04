package com.example.demo.firma.repository;


import com.example.demo.firma.entity.Pracownik;
import com.example.demo.firma.entity.Szef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface PracownikRepository extends JpaRepository<Pracownik, String> {



    public Optional<Pracownik> findById(int id);
    List<Pracownik> findAllBySzef(Szef szef);
    Optional<Pracownik> findByIdAndSzef(int id, Szef szef);
    public List<Pracownik> findAll();


}

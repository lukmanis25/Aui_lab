package com.example.demo.firma.repository;


import com.example.demo.Storage;
import com.example.demo.firma.entity.Pracownik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface PracownikRepository extends JpaRepository<Pracownik, String> {



    public Optional<Pracownik> findById(long id);


    public List<Pracownik> findAll();


}

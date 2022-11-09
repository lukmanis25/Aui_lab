package com.example.demo;

import com.example.demo.firma.entity.Pracownik;
import com.example.demo.firma.entity.Szef;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class Storage {

    public List<Szef> szefowie=new ArrayList<Szef>();
    public List<Pracownik> pracownicy=new ArrayList<Pracownik>();

}

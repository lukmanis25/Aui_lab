package com.example.demo;

import com.example.demo.firma.entity.Pracownik;
import com.example.demo.firma.entity.Szef;
import com.example.demo.firma.service.PracownikService;
import com.example.demo.firma.service.SzefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;


/**
 * Component for interaction with user using command line. Components annotated with {@link @Component} implementing
 * {@link CommandLineRunner} are executed automatically.
 */
@Component
public class CommandLine implements CommandLineRunner {

    private PracownikService pracownikService;
    private SzefService szefService;

    @Autowired
    public CommandLine(PracownikService pracownikService, SzefService szefService) {
        this.pracownikService = pracownikService;
        this.szefService = szefService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello world");
       // characterService.findAll().forEach(System.out::println);
        pracownikService.findAll().forEach(System.out::println);
        szefService.findAll().forEach(System.out::println);
        System.out.println(szefService.find(1));



    }

}

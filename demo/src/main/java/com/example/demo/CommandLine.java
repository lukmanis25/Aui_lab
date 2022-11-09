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

        while(true){
            System.out.println("COMMANDS:");
            System.out.println("0. Quit");
            System.out.println("1. Print categories ");
            System.out.println("2. Print all elements");
            System.out.println("3. Find element by id");
            System.out.println("4. Add new element");
            System.out.println("5. Del element");
            Scanner input = new Scanner(System.in);
            int num = input.nextInt();
            System.out.println(" ");
            System.out.println("-----------------------------------");
            switch(num) {
                case 0:
                    break;
                case 1:
                    System.out.println("Categories:");
                    System.out.println("1. pracownik");
                    System.out.println("2. szef");
                    break;
                case 2:
                    System.out.print("write categorie num (1. pracownik | 2. szef): ");
                    int num2 = input.nextInt();
                    if(num2 == 1){
                        pracownikService.findAll().forEach(System.out::println);
                    }
                    else if(num2 == 2){
                        szefService.findAll().forEach(System.out::println);
                    }
                    else{
                        System.out.println("Wrong categorie");
                    }
                    break;
                case 3:
                    System.out.print("write categorie num (1. pracownik | 2. szef): ");
                    int num3 = input.nextInt();
                    int id;
                    if(num3 == 1){
                        System.out.print("write id: ");
                        id = input.nextInt();
                        Optional<Pracownik> p = pracownikService.findById(id);
                        if(p.isPresent()){
                            System.out.println(p.get());
                        }
                        else{
                            System.out.println("Id doesnt exist");
                        }

                    }
                    else if(num3 == 2){
                        System.out.print("write id: ");
                        id = input.nextInt();
                        Optional<Szef> sz = szefService.findById(id);
                        if(sz.isPresent()){
                            System.out.println(sz.get());
                        }
                        else{
                            System.out.println("Id doesnt exist");
                        }
                    }
                    else{
                        System.out.println("Wrong categorie");
                    }
                    break;
                case 4:
                    System.out.print("write categorie num (1. pracownik | 2. szef): ");
                    int num4 = input.nextInt();
                    int new_id;
                    String new_name;
                    int szef_id;
                    if(num4 == 1){
                        System.out.print("write id: ");
                        new_id = input.nextInt();
                        Optional<Pracownik> p = pracownikService.findById(new_id);
                        if(p.isPresent()){
                            System.out.println("Id already exist");
                            break;
                        }
                        System.out.print("write szef id: ");
                        szef_id = input.nextInt();
                        Optional<Szef> sz = szefService.findById(szef_id);
                        if(sz.isEmpty()){
                            System.out.println("Szef doesnt exist");
                            break;
                        }
                        System.out.println("write name: ");
                        new_name = input.next();
                        pracownikService.create(new Pracownik(new_id, new_name, sz.get()));
                        break;
                    }
                    else if(num4 == 2){
                        System.out.print("write id: ");
                        new_id = input.nextInt();
                        Optional<Szef> sz = szefService.findById(new_id);
                        if(sz.isPresent()){
                            System.out.println("Id already exist");
                            break;
                        }
                        System.out.println("write name: ");
                        new_name = input.next();
                        szefService.create(new Szef(new_id, new_name));
                        break;
                    }
                    else{
                        System.out.println("Wrong categorie");
                    }
                    break;
                case 5:
                    System.out.print("write categorie num (1. pracownik | 2. szef): ");
                    int num5 = input.nextInt();
                    int del_id;
                    if(num5 == 1){
                        System.out.print("write id: ");
                        del_id = input.nextInt();
                        Optional<Pracownik> p = pracownikService.findById(del_id);
                        if(p.isEmpty()){
                            System.out.println("Id doesnt exist");
                            break;
                        }
                        pracownikService.del(del_id);
                    }
                    else if(num5 == 2){
                        System.out.print("write id: ");
                        del_id = input.nextInt();
                        Optional<Szef> sz = szefService.findById(del_id);
                        if(sz.isEmpty()){
                            System.out.println("Id doesnt exist");
                            break;
                        }
                        szefService.del(del_id);
                    }
                    else{
                        System.out.println("Wrong categorife");
                    }
                    break;

                default:
                    System.out.println("Command doesnt exist");

            }

            System.out.println("-----------------------------------");
            System.out.println(" ");
            if(num == 0){
                break;
            }
        }


    }

}

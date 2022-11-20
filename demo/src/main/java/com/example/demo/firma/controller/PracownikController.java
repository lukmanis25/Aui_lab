package com.example.demo.firma.controller;

import com.example.demo.firma.dto.GetPracownikResponse;
import com.example.demo.firma.dto.GetPracowniksResponse;
import com.example.demo.firma.service.PracownikService;
import com.example.demo.firma.service.SzefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;


@RestController
@RequestMapping("api/pracowniks")
public class PracownikController {
    private SzefService szefService;
    private PracownikService pracownikService;

    @Autowired
    public PracownikController(SzefService szefService, PracownikService pracownikService){
        this.szefService = szefService;
        this.pracownikService = pracownikService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})//When empty JSON is default
    public ResponseEntity<GetPracowniksResponse> getCharacters() {
        return ResponseEntity.ok(GetPracowniksResponse.entityToDtoMapper().apply(pracownikService.findAll()));
    }
    @GetMapping("{id}")
    public ResponseEntity<GetPracownikResponse> getCharacter(@PathVariable("id") int id) {
        return  pracownikService.find(id)
                .map(value -> ResponseEntity.ok(GetPracownikResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}

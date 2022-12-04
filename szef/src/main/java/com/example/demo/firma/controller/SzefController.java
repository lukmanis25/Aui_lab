package com.example.demo.firma.controller;

import com.example.demo.firma.dto.*;
import com.example.demo.firma.entity.Szef;
import com.example.demo.firma.service.SzefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/szefs")
public class SzefController {

    private SzefService szefService;

    @Autowired
    public SzefController(SzefService szefService){
        this.szefService = szefService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})//When empty JSON is default
    public ResponseEntity<GetSzefsResponse> getSzefs() {
//        return ResponseEntity.ok(GetCharactersResponse.entityToDtoMapper().apply(characterService.findAll()));
        List<Szef> all = szefService.findAll();
        Function<Collection<Szef>, GetSzefsResponse> mapper = GetSzefsResponse.entityToDtoMapper();
        GetSzefsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{szef_id}")
    public ResponseEntity<GetSzefResponse> getSzef(@PathVariable("szef_id") int szef_id) {
        return szefService.find(szef_id)
                .map(value -> ResponseEntity
                        .ok(GetSzefResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }



    @DeleteMapping("{szef_id}")
    public ResponseEntity<Void> deleteSzef(@PathVariable("szef_id") int szef_id) {
        Optional<Szef> szef = szefService.find(szef_id);
        if (szef.isPresent()) {
            szefService.delete(szef.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> postSzef(@RequestBody PostSzefRequest request, UriComponentsBuilder builder) {
        Szef szef = PostSzefRequest.dtoToEntityMapper().apply(request);
        szefService.save(szef);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "szefs", "{szef_id}")
                        .buildAndExpand(szef.getId()).toUri())
                .build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putSzef(@RequestBody PutSzefRequest request, @PathVariable("id") int id) {
        Optional<Szef> szef = szefService.find(id);
        if (szef.isPresent()) {
            PutSzefRequest.dtoToEntityUpdater().apply(szef.get(), request);
            szefService.update(szef.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}


package com.example.demo.firma.event.repository;

import com.example.demo.firma.entity.Szef;
import com.example.demo.firma.event.dto.PostSzefRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class SzefEventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public SzefEventRepository(@Value("${rpg.pracowniks.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Szef szef) {
        restTemplate.delete("/szefs/{id}", szef.getId());
    }

    public void create(Szef szef) {
        restTemplate.postForLocation("/szefs", PostSzefRequest.entityToDtoMapper().apply(szef));
    }
}

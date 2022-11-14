package com.example.demo.firma.dto;

import com.example.demo.firma.entity.Szef;
import lombok.*;

import java.util.function.Function;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostSzefRequest {
    private int id;
    private String imie;
    public static Function<PostSzefRequest, Szef> dtoToEntityMapper() {
        return request -> Szef.builder()
                .imie(request.getImie())
                .id(request.getId())
                .build();
    }
}

package com.example.demo.firma.dto;

import com.example.demo.firma.entity.Szef;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutSzefRequest {
    private String imie;

    public static BiFunction<Szef, PutSzefRequest, Szef> dtoToEntityUpdater() {
        return (szef, request) -> {
            szef.setImie(request.getImie());
            return szef;
        };
    }
}

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
public class GetSzefResponse {
    private int id;
    private String imie;

    public static Function<Szef, GetSzefResponse> entityToDtoMapper() {
        return szef -> GetSzefResponse.builder()
                .id(szef.getId())
                .imie(szef.getImie())
                .build();
    }
}


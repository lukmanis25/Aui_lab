package com.example.demo.firma.dto;

import com.example.demo.firma.entity.Pracownik;
import com.example.demo.firma.entity.Szef;
import lombok.*;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostPracownikRequest {
    private int id;
    private String imie;
    public static Function<PostPracownikRequest, Pracownik> dtoToEntityMapper(
            Supplier<Szef> szefSupplier) {
        return request -> Pracownik.builder()
                .imie(request.getImie())
                .id(request.getId())
                .szef(szefSupplier.get())
                .build();
    }

}

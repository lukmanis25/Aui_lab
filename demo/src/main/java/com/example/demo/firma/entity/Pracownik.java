package com.example.demo.firma.entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode
@Entity
@Table(name = "pracownicy")
public class Pracownik {
    @Id
    private int id;
    private String imie;
    @ManyToOne
    @JoinColumn(name ="szef")
    private Szef szef;
}

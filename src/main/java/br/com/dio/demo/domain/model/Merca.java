package br.com.dio.demo.domain.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Merca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    @Column(nullable = false)
    private String descricao;
}

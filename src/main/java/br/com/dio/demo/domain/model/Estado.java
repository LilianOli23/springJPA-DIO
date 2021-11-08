package br.com.dio.demo.domain.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String estado;



}

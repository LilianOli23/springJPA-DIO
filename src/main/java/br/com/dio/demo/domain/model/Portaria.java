package br.com.dio.demo.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Portaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_cpf", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @Column(name = "datahora_entrada", nullable = false)
    private LocalDateTime dataHoraEntrada;

    @Column(name = "datahora_saida", nullable = false)
    private LocalDateTime dataHoraSaida;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;



}

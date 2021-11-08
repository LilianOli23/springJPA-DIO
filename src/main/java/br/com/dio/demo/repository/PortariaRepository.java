package br.com.dio.demo.repository;

import br.com.dio.demo.domain.model.Portaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortariaRepository extends JpaRepository<Portaria, Long> {
}

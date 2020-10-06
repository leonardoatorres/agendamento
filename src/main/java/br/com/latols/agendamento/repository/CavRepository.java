package br.com.latols.agendamento.repository;

import br.com.latols.agendamento.entity.Cav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CavRepository extends JpaRepository<Cav, Integer> {
}

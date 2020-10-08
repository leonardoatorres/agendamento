package br.com.latols.agendamento.repository;

import br.com.latols.agendamento.entity.Cav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CavRepository extends JpaRepository<Cav, Integer> {

    @Query(value = "SELECT * FROM cav where cav.name = ?" ,nativeQuery = true)
    Optional<Cav> selectCav(String cav);

    @Query(value = "SELECT cav.id FROM cav where cav.name = ?" ,nativeQuery = true)
    int selectIdCavPorNome(String cav);

}

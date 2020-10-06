package br.com.latols.agendamento.repository;

import br.com.latols.agendamento.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query(value = "SELECT car.id FROM car where car.model = ?" ,nativeQuery = true)
    int selectIdCarPorNome(String name);

}

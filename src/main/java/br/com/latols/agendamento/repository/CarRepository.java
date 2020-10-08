package br.com.latols.agendamento.repository;

import br.com.latols.agendamento.entity.Car;
import br.com.latols.agendamento.entity.Cav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query(value = "SELECT car.id FROM car where car.model = ?" ,nativeQuery = true)
    int selectIdCarPorNome(String name);

    @Query(value = "SELECT * FROM car where car.model = ?" ,nativeQuery = true)
    Optional<Car> selectCar(String car);

}

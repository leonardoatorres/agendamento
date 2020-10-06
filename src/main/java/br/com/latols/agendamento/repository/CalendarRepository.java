package br.com.latols.agendamento.repository;

import br.com.latols.agendamento.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {

    @Query(value = "SELECT * FROM calendar where calendar.date = ? and calendar.cav = ? and calendar.hour = ? and calendar.tipo = ?" ,nativeQuery = true)
    Optional<Calendar> selectDateAndCavAndHourAndTipo(LocalDate date, String cav, int hour, String tipo);
}
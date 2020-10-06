package br.com.latols.agendamento.service;

import br.com.latols.agendamento.controller.to.CalendarTO;
import br.com.latols.agendamento.entity.Calendar;
import br.com.latols.agendamento.repository.CalendarRepository;
import br.com.latols.agendamento.repository.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalendarServiceTest {

    @InjectMocks
    private CalendarService calendarService;

    @Mock
    private CalendarRepository calendarRepository;

    @Mock
    private CarRepository carRepository;

    @Test
    public void deveAgendar(){
        CalendarTO to = new CalendarTO();
        to.setCarro("Fiesta");
        to.setCav("Botafogo");
        to.setDate(LocalDate.now());
        to.setTipo("inspection");
        when(carRepository.selectIdCarPorNome(to.getCarro())).thenReturn(12);
        when(calendarRepository.selectDateAndCavAndHourAndTipo(eq(to.getDate()),eq(to.getCav()), eq(to.getHour()), eq(to.getTipo()))).thenReturn(Optional.empty());
        calendarService.agendar(to);
        verify(calendarRepository, times(1)).save(any(Calendar.class));
    }

    @Test
    public void naoDeveAgendar(){
        Calendar calendar = new Calendar();
        calendar.setDate(LocalDate.now());
        calendar.setCav("Botafogo");
        calendar.setTipo("inpection");
        calendar.setHour(12);
        calendar.setCar(1);
        CalendarTO to = new CalendarTO();
        to.setCarro("Fiesta");
        to.setCav("Botafogo");
        to.setDate(LocalDate.now());
        to.setHour(12);
        to.setTipo("inspection");
        when(calendarRepository.selectDateAndCavAndHourAndTipo(eq(to.getDate()),eq(to.getCav()), eq(to.getHour()), eq(to.getTipo()))).thenReturn(Optional.empty());
        calendarService.agendar(to);
        verify(calendarRepository, never()).save(calendar);
    }

}
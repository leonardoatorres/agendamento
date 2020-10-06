package br.com.latols.agendamento.service;

import br.com.latols.agendamento.controller.to.CalendarTO;
import br.com.latols.agendamento.entity.Calendar;
import br.com.latols.agendamento.repository.CalendarRepository;
import br.com.latols.agendamento.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarService {

    private final CalendarRepository repository;

    private final CarRepository carRepository;

    public CalendarService(CalendarRepository repository, CarRepository carRepository) {
        this.repository = repository;
        this.carRepository = carRepository;
    }

    public List<Calendar> getCalendar() {
        return repository.findAll();
    }

    public void agendar(CalendarTO to) {
        Optional preenchido = repository.selectDateAndCavAndHourAndTipo(to.getDate(),to.getCav(), to.getHour(), to.getTipo());
        if(preenchido.isPresent()){
            System.out.println("Ja possui uma marcacao para esse horario");
            return;
        }
        int idCar = carRepository.selectIdCarPorNome(to.getCarro());
        Calendar calendar = new Calendar();
        calendar.setDate(to.getDate());
        calendar.setCav(to.getCav());
        calendar.setTipo(to.getTipo());
        calendar.setHour(to.getHour());
        calendar.setCar(idCar);
        repository.save(calendar);
    }
}

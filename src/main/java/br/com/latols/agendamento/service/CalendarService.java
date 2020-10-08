package br.com.latols.agendamento.service;

import br.com.latols.agendamento.controller.to.CalendarTO;
import br.com.latols.agendamento.entity.Calendar;
import br.com.latols.agendamento.repository.CalendarRepository;
import br.com.latols.agendamento.repository.CarRepository;
import br.com.latols.agendamento.repository.CavRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarService {

    private final CalendarRepository repository;

    private final CarRepository carRepository;

    private final CavRepository cavRepository;

    public CalendarService(CalendarRepository repository, CarRepository carRepository, CavRepository cavRepository) {
        this.repository = repository;
        this.carRepository = carRepository;
        this.cavRepository = cavRepository;
    }

    public ResponseEntity getCalendar() {
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }

    List<Integer> horario = Arrays.asList(10, 11, 12, 13, 14, 15, 16, 17);

    public ResponseEntity agendar(CalendarTO to) {
        Optional preenchido = repository.selectDateAndCavAndHourAndTipo(to.getDate(),to.getCav(), to.getHour(), to.getTipo());
        Optional cav = cavRepository.selectCav(to.getCav());
        Optional car = carRepository.selectCar(to.getCarro());
        if(preenchido.isPresent()){
            return new ResponseEntity("Já existe marcação para este horário", HttpStatus.BAD_REQUEST);
        }
        if(!car.isPresent()){
            return new ResponseEntity("Carro inválido", HttpStatus.BAD_REQUEST);
        }
        if(cav.isEmpty()){
            return new ResponseEntity("Cav inválido", HttpStatus.BAD_REQUEST);
        }
        if(!(horario.contains(to.getHour()))){
            return new ResponseEntity("Horário Inválido", HttpStatus.BAD_REQUEST);
        }
        int idCar = carRepository.selectIdCarPorNome(to.getCarro());
        Calendar calendar = new Calendar();
        calendar.setDate(to.getDate());
        calendar.setCav(to.getCav());
        calendar.setTipo(to.getTipo());
        calendar.setHour(to.getHour());
        calendar.setCar(idCar);
        repository.save(calendar);
        return new ResponseEntity("Operação Realizada com Sucesso", HttpStatus.CREATED);
    }
}

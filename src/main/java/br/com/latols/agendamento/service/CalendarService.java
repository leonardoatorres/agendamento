package br.com.latols.agendamento.service;

import br.com.latols.agendamento.controller.to.CalendarTO;
import br.com.latols.agendamento.entity.Calendar;
import br.com.latols.agendamento.repository.CalendarRepository;
import br.com.latols.agendamento.repository.CarRepository;
import br.com.latols.agendamento.repository.CavRepository;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.com.latols.agendamento.controller.logger.AgendamentoLogger.*;

@Service
public class CalendarService {

    private final CalendarRepository repository;

    private final CarRepository carRepository;

    private final CavRepository cavRepository;

    private final static Logger logger = Logger.getLogger(CalendarService.class);

    public CalendarService(CalendarRepository repository, CarRepository carRepository, CavRepository cavRepository) {
        this.repository = repository;
        this.carRepository = carRepository;
        this.cavRepository = cavRepository;
    }

    public ResponseEntity getCalendar() {
        logger.info(EVENT_RETORNAR_AGENDAMENTOS);
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }

    List<Integer> horario = Arrays.asList(10, 11, 12, 13, 14, 15, 16, 17);

    public ResponseEntity agendar(CalendarTO to) {

        logger.info(EVENT_AGENDAR);

        Optional preenchido = repository.selectDateAndCavAndHourAndTipo(to.getDate(),to.getCav(), to.getHour(), to.getTipo());
        Optional cav = cavRepository.selectCav(to.getCav());
        Optional car = carRepository.selectCar(to.getCarro());
        if(cav.isEmpty()){
            logger.error(CAV_INVALIDO);
            return new ResponseEntity(CAV_INVALIDO, HttpStatus.BAD_REQUEST);
        }
        if(!isTipoValido(to.getTipo())){
            logger.error(TIPO_INVALIDO);
            return new ResponseEntity(TIPO_INVALIDO, HttpStatus.BAD_REQUEST);
        }
        if(!car.isPresent()){
            logger.error(CARRO_INVALIDO);
            return new ResponseEntity(CARRO_INVALIDO, HttpStatus.BAD_REQUEST);
        }
        if(!(horario.contains(to.getHour()))){
            logger.error(HORARIO_INVALIDO);
            return new ResponseEntity(HORARIO_INVALIDO, HttpStatus.BAD_REQUEST);
        }
        if(preenchido.isPresent()){
            logger.error(MARCACAO_JA_EXISTE);
            return new ResponseEntity(MARCACAO_JA_EXISTE, HttpStatus.BAD_REQUEST);
        }
        int idCar = carRepository.selectIdCarPorNome(to.getCarro());
        Calendar calendar = new Calendar();
        calendar.setDate(to.getDate());
        calendar.setCav(to.getCav());
        calendar.setTipo(to.getTipo());
        calendar.setHour(to.getHour());
        calendar.setCar(idCar);
        repository.save(calendar);
        return new ResponseEntity(OPERACAO_SUCESSO, HttpStatus.CREATED);
    }

    public boolean isTipoValido(String tipo){
        if(tipo.equals("visit") || tipo.equals("inspection")){
            return true;
        }
        return false;
    }
}

package br.com.latols.agendamento.service;

import br.com.latols.agendamento.controller.to.CarTO;
import br.com.latols.agendamento.entity.Car;
import br.com.latols.agendamento.repository.CarRepository;
import br.com.latols.agendamento.repository.CavRepository;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.latols.agendamento.controller.logger.AgendamentoLogger.*;

@Service
public class CarService {

    private final CarRepository repository;

    private final CavRepository cavRepository;

    private final static Logger logger = Logger.getLogger(CarService.class);

    public CarService(CarRepository repository, CavRepository cavRepository) {
        this.repository = repository;
        this.cavRepository = cavRepository;
    }

    public ResponseEntity getCars() {
        logger.info(EVENT_RETORNAR_CARROS);
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity adicionar(CarTO to){
        logger.info(EVENT_ADICIONAR_CARRO);
        Optional cav = cavRepository.selectCav(to.getCav());
        if(cav.isEmpty()){
            logger.error(CAV_INVALIDO);
            return new ResponseEntity(CAV_INVALIDO, HttpStatus.BAD_REQUEST);
        }
        Car car = new Car();
        car.setBrand(to.getBrand());
        car.setModel(to.getModel());
        car.setCav(to.getCav());
        repository.save(car);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    public ResponseEntity remover(String name){
        logger.info(EVENT_REMOVER_CARRO);
        int id = repository.selectIdCarPorNome(name);
        repository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

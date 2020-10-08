package br.com.latols.agendamento.service;

import br.com.latols.agendamento.controller.to.CarTO;
import br.com.latols.agendamento.entity.Car;
import br.com.latols.agendamento.repository.CarRepository;
import br.com.latols.agendamento.repository.CavRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    private final CarRepository repository;

    private final CavRepository cavRepository;

    public CarService(CarRepository repository, CavRepository cavRepository) {
        this.repository = repository;
        this.cavRepository = cavRepository;
    }

    public ResponseEntity getCars() {
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity adicionar(CarTO to){
        Optional cav = cavRepository.selectCav(to.getCav());
        if(cav.isEmpty()){
            return new ResponseEntity("Cav inválido", HttpStatus.BAD_REQUEST);
        }
        Car car = new Car();
        car.setBrand(to.getBrand());
        car.setModel(to.getModel());
        car.setCav(to.getCav());
        repository.save(car);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    public ResponseEntity remover(String name){
        int id = repository.selectIdCarPorNome(name);
        repository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

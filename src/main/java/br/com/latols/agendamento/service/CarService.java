package br.com.latols.agendamento.service;

import br.com.latols.agendamento.entity.Car;
import br.com.latols.agendamento.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public List<Car> getCars() {
        return repository.findAll();
    }
}

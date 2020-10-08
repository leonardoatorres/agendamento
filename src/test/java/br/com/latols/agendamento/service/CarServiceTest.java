package br.com.latols.agendamento.service;

import br.com.latols.agendamento.controller.to.CarTO;
import br.com.latols.agendamento.entity.Car;
import br.com.latols.agendamento.entity.Cav;
import br.com.latols.agendamento.repository.CarRepository;
import br.com.latols.agendamento.repository.CavRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private CavRepository cavRepository;

    @Test
    public void deveAdicionar(){
        CarTO to = new CarTO();
        to.setBrand("VW");
        to.setModel("Golf");
        to.setCav("Botafogo");
        when(cavRepository.selectCav(eq(to.getCav()))).thenReturn(Optional.of(new Cav()));
        Car car = new Car();
        car.setCav("Botafogo");
        car.setBrand("VW");
        car.setModel("Golf");
        car.setId(1);
        carService.adicionar(to);
        verify(carRepository, times(1)).save(any(Car.class));
    }

    @Test
    public void naoDeveAdicionar(){
        CarTO to = new CarTO();
        to.setBrand("VW");
        to.setModel("Golf");
        to.setCav("Botafogo");
        when(cavRepository.selectCav(to.getCav())).thenReturn(Optional.empty());
        carService.adicionar(to);
        verify(carRepository, never() ).save(any(Car.class));
    }

}
package br.com.latols.agendamento.controller;

import br.com.latols.agendamento.controller.to.CalendarTO;
import br.com.latols.agendamento.controller.to.CarTO;
import br.com.latols.agendamento.controller.to.CavTO;
import br.com.latols.agendamento.entity.Calendar;
import br.com.latols.agendamento.entity.Car;
import br.com.latols.agendamento.entity.Cav;
import br.com.latols.agendamento.service.CalendarService;
import br.com.latols.agendamento.service.CarService;
import br.com.latols.agendamento.service.CavService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CalendarController {

    private final CavService cavService;

    private final CarService carService;

    private final CalendarService calendarService;

    public CalendarController(CavService cavService, CarService carService, CalendarService calendarService) {
        this.cavService = cavService;
        this.carService = carService;
        this.calendarService = calendarService;
    }

    @GetMapping("/cav")
    public ResponseEntity<List<Cav>> findAllCavs() {
        return cavService.getCavs();
    }

    @PostMapping("/cav")
    public ResponseEntity saveCav(@RequestBody CavTO cavTO) {
        return cavService.adicionar(cavTO);
    }

    @DeleteMapping("/cav/{name}")
    public ResponseEntity removeCav(@PathVariable String name) {
        return cavService.remover(name);
    }

    @GetMapping("/car")
    public ResponseEntity<List<Car>> findAllCars() {
        return carService.getCars();
    }

    @PostMapping("/car")
    public ResponseEntity saveCav(@RequestBody CarTO carTO) {
        return carService.adicionar(carTO);
    }

    @DeleteMapping("/car/{name}")
    public ResponseEntity removeCar(@PathVariable String name) {
        return carService.remover(name);
    }

    @GetMapping("/calendar")
    public ResponseEntity<List<Calendar>> findAllCalendar() {
        return calendarService.getCalendar();
    }

    @PostMapping("/calendar")
    public ResponseEntity saveCalendar(@RequestBody CalendarTO calendarTO) {
        return calendarService.agendar(calendarTO);
    }

}
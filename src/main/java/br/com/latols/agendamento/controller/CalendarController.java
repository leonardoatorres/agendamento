package br.com.latols.agendamento.controller;

import br.com.latols.agendamento.controller.to.CalendarTO;
import br.com.latols.agendamento.entity.Calendar;
import br.com.latols.agendamento.entity.Car;
import br.com.latols.agendamento.entity.Cav;
import br.com.latols.agendamento.service.CalendarService;
import br.com.latols.agendamento.service.CarService;
import br.com.latols.agendamento.service.CavService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Cav> findAllCavs() {
        return cavService.getCavs();
    }

    @GetMapping("/car")
    public List<Car> findAllCars() {
        return carService.getCars();
    }

    @GetMapping("/calendar")
    public List<Calendar> findAllCalendar() {
        return calendarService.getCalendar();
    }

    @PostMapping("/calendar")
    public void saveAgendamento(@RequestBody CalendarTO calendarTO) {
        calendarService.agendar(calendarTO);
    }

}
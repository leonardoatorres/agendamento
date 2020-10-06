package br.com.latols.agendamento.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "calendar")
public class Calendar {

    @Column(name = "date")
    private LocalDate date;
    @Column(name = "cav")
    private String cav;
    @Column(name = "tipo")
    private String tipo;
    @Id
    @Column(name = "car")
    private int car;
    @Column(name = "hour")
    private int hour;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCav() {
        return cav;
    }

    public void setCav(String cav) {
        this.cav = cav;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}

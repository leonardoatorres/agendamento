package br.com.latols.agendamento.controller.to;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class CalendarTO {

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private String cav;
    private String tipo;
    private String carro;
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

    public String getCarro() {
        return carro;
    }

    public void setCarro(String carro) {
        this.carro = carro;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}

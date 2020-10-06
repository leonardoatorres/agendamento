package br.com.latols.agendamento.service;

import br.com.latols.agendamento.entity.Cav;
import br.com.latols.agendamento.repository.CavRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CavService {

    private final CavRepository repository;

    public CavService(CavRepository repository) {
        this.repository = repository;
    }

    public List<Cav> getCavs() {
        return repository.findAll();
    }
}

package br.com.latols.agendamento.service;

import br.com.latols.agendamento.controller.to.CavTO;
import br.com.latols.agendamento.entity.Cav;
import br.com.latols.agendamento.repository.CavRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CavService {

    private final CavRepository repository;

    public CavService(CavRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity getCavs() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity adicionar(CavTO to){
        Optional resultado = repository.selectCav(to.getName());
        if(resultado.isPresent()){
            return new ResponseEntity("Cav já cadastrado", HttpStatus.BAD_REQUEST);
        }
        Cav cav = new Cav();
        cav.setName(to.getName());
        repository.save(cav);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    public ResponseEntity remover(String name){
        int id = repository.selectIdCavPorNome(name);
        repository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

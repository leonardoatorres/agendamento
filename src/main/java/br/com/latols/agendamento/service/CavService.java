package br.com.latols.agendamento.service;

import br.com.latols.agendamento.controller.to.CavTO;
import br.com.latols.agendamento.entity.Cav;
import br.com.latols.agendamento.repository.CavRepository;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.latols.agendamento.controller.logger.AgendamentoLogger.*;

@Service
public class CavService {

    private final CavRepository repository;

    private final static Logger logger = Logger.getLogger(CavService.class);

    public CavService(CavRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity getCavs() {
        logger.info(EVENT_RETORNAR_CAVS);
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity adicionar(CavTO to){
        logger.info(EVENT_ADICIONAR_CAV);
        Optional resultado = repository.selectCav(to.getName());
        if(resultado.isPresent()){
            logger.info(CAV_JA_CADASTRADO);
            return new ResponseEntity(CAV_JA_CADASTRADO, HttpStatus.BAD_REQUEST);
        }
        Cav cav = new Cav();
        cav.setName(to.getName());
        repository.save(cav);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    public ResponseEntity remover(String name){
        logger.info(EVENT_REMOVER_CAV);
        int id = repository.selectIdCavPorNome(name);
        repository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

package br.com.latols.agendamento.service;

import br.com.latols.agendamento.controller.to.CavTO;
import br.com.latols.agendamento.entity.Cav;
import br.com.latols.agendamento.repository.CavRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CavServiceTest {

    @InjectMocks
    private CavService cavService;

    @Mock
    private CavRepository cavRepository;

    @Test
    public void deveAdicionar(){
        CavTO to = new CavTO();
        to.setName("Norte Shopping");
        when(cavRepository.selectCav(eq(to.getName()))).thenReturn(Optional.empty());
        cavService.adicionar(to);
        verify(cavRepository, times(1)).save(any(Cav.class));
    }

    @Test
    public void naoDeveAdicionar(){
        CavTO to = new CavTO();
        to.setName("Norte Shopping");
        when(cavRepository.selectCav(eq(to.getName()))).thenReturn(Optional.of(new Cav()));
        cavService.adicionar(to);
        verify(cavRepository, never()).save(any(Cav.class));
    }

}
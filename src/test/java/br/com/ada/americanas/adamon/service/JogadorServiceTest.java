package br.com.ada.americanas.adamon.service;

import br.com.ada.americanas.adamon.model.Adamon;
import br.com.ada.americanas.adamon.model.Jogador;
import br.com.ada.americanas.adamon.repository.JogadorRepository;
import br.com.ada.americanas.adamon.util.TesteUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.ada.americanas.adamon.util.TesteUtils.obterAdamon;
import static br.com.ada.americanas.adamon.util.TesteUtils.obterJogador;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JogadorServiceTest {

    @InjectMocks
    private JogadorService jogadorService;
    @Mock
    private JogadorRepository jogadorRepository;

    @Test
    void deveConseguirComprarAdamon(){
        //cenário
        Jogador jogador = new Jogador();
        Adamon adamon = new Adamon();
        when(jogadorRepository.findById(jogador.getId())).thenReturn(Optional.of(jogador));

        //ação
        jogadorService.buyAdamon(jogador, adamon);

        //verificação
        assertFalse(jogador.getAdamons().isEmpty());
        Mockito.verify(jogadorRepository).save(jogador); // se o repository salvou o jogador?
    }

    @Test
    public void testComprarAdamon() {
        Jogador jogador = new Jogador();
        Adamon adamon = new Adamon();

        when(jogadorRepository.save(jogador)).thenReturn(jogador);

        jogadorService.buyAdamon(jogador, adamon);

        assertEquals(new BigDecimal("100"), jogador.getSaldo());
        assertTrue(jogador.getAdamons().contains(adamon));
    }

    @Test
    public void testComprarAdamonSaldoInsuficiente() {
        //Cenário
        Jogador jogador = obterJogador();
        jogador.setSaldo(BigDecimal.valueOf(0));//saldo 0
        Adamon adamon = obterAdamon();

        //Ação
        Assertions.assertThrows(RuntimeException.class, () -> {jogadorService.buyAdamon(jogador, adamon);});
    }

    @Test
    public void testNaoDeveComprarAdamonEquipeCheia() {
        //Cenário
        Jogador jogador = obterJogador();
        jogador.setAdamons(TesteUtils.obterAdamons()); //equipe cheia
        Adamon adamon = obterAdamon();

        //Ação
        Assertions.assertThrows(RuntimeException.class, () -> {jogadorService.buyAdamon(jogador, adamon);});
    }
}

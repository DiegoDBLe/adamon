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
    JogadorService jogadorService;

    @Mock
    private JogadorRepository repository;


    @Test
    void deveConseguirComprarAdamon() {
        //cenário
        Jogador jogador = obterJogador();
        Adamon adamon = obterAdamon();
        jogador.setSaldo(BigDecimal.valueOf(100));
        Mockito.when(repository.findById(jogador.getId())).thenReturn(Optional.of(jogador));
        //acao
        jogadorService.buyAdamon(jogador, adamon);

        //verificação
        assertFalse(jogador.getAdamons().isEmpty());
        Mockito.verify(repository).save(jogador);
    }

    @Test
    void naoDeveConseguirComprarAdamonNaoPossuiSaldo() {
        //cenário
        Jogador jogador = obterJogador();
        jogador.setSaldo(BigDecimal.valueOf(0)); //saldo 0
        Adamon adamon = obterAdamon();

        //acao e verificação
        Assertions.assertThrows(RuntimeException.class, () -> { jogadorService.buyAdamon(jogador, adamon); });
    }

    @Test
    void naoDeveConseguirComprarAdamonPoisEquipeEstaCheia() {
        //cenário
        Jogador jogador = obterJogador();
        jogador.setAdamons(TesteUtils.obterAdamons()); //equipe cheia
        Adamon adamon = obterAdamon();

        //acao
        Assertions.assertThrows(RuntimeException.class, () -> { jogadorService.buyAdamon(jogador, adamon); });
    }
}

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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.ada.americanas.adamon.util.TesteUtils.obterAdamon;
import static br.com.ada.americanas.adamon.util.TesteUtils.obterJogador;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class JogadorServiceTest {
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
        Assertions.assertThrows(RuntimeException.class, () -> {
            jogadorService.buyAdamon(jogador, adamon);
        });
    }

    @Test
    void naoDeveConseguirComprarAdamonPoisEquipeEstaCheia() {
        //cenário
        Jogador jogador = obterJogador();
        jogador.setAdamons(TesteUtils.obterAdamons()); //equipe cheia
        Adamon adamon = obterAdamon();

        //acao
        Assertions.assertThrows(RuntimeException.class, () -> {
            jogadorService.buyAdamon(jogador, adamon);
        });
    }

    @Test
     void testTemAdamonsVivos() {
        List<Adamon> equipe = new ArrayList<>();
        Adamon adamon = new Adamon();
        adamon.setName("AdaChu");
        adamon.setVida(0);

        Adamon adamon2 = new Adamon();
        adamon2.setName("Charmander");
        adamon2.setVida(0);

        equipe.add(adamon);
        equipe.add(adamon2);


        assertFalse(jogadorService.temAdamonsVivos(equipe));
    }

    @Test
     void testEscolherAdamon() {
        List<Adamon> equipe = new ArrayList<>();
        Adamon adamon = new Adamon();
        adamon.setName("AdaChu");
        adamon.setVida(10);

        Adamon adamon2 = new Adamon();
        adamon2.setName("Charmander");
        adamon2.setVida(0);

        equipe.add(adamon);
        equipe.add(adamon2);

        assertEquals(jogadorService.escolherAdamon(equipe), equipe.get(0));
    }

    @Test
     void testBatalhar() {
        Jogador jogador1 = new Jogador();
        jogador1.setNickname("Ash");

        Jogador jogador2 = new Jogador();
        jogador2.setNickname("Ada");

        Adamon adamon1 = new Adamon();
        adamon1.setName("AdaChu");
        adamon1.setVida(10);

        Adamon adamon2 = new Adamon();
        adamon2.setName("CharizarAda");
        adamon2.setVida(0);

        Adamon adamon3 = new Adamon();
        adamon3.setName("SquirtleAda");
        adamon3.setVida(0);

        List<Adamon> equipe1 = new ArrayList<>();
        equipe1.add(adamon1);
        equipe1.add(adamon2);
        equipe1.add(adamon3);

        List<Adamon> equipe2 = new ArrayList<>();
        equipe2.add(adamon3);
        equipe2.add(adamon3);
        equipe2.add(adamon2);

        jogador1.setAdamons(equipe1);
        jogador2.setAdamons(equipe2);

        jogadorService.batalhar(jogador1, jogador2);

        // Verifica se algum jogador ganhou a batalha
        assertTrue(!jogadorService.temAdamonsVivos(jogador1.getAdamons()) || !jogadorService.temAdamonsVivos(jogador2.getAdamons()));
    }
}

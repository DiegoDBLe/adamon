package br.com.ada.americanas.adamon.service;

import br.com.ada.americanas.adamon.model.Adamon;
import br.com.ada.americanas.adamon.model.Inventario;
import br.com.ada.americanas.adamon.model.Jogador;
import br.com.ada.americanas.adamon.util.TesteUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class InventarioServiceTest {

    @Test
    void deveDpositarAdamonNoInventario(){
        //Cenário
        Jogador jogador = new Jogador();
        jogador.setAdamons(TesteUtils.obterAdamons());
        InventarioService inventarioService = new InventarioService();

        Inventario inventario = new Inventario();
        inventario.setJogador(jogador);

        //Execução
        inventarioService.depositAdamon(inventario, jogador.getAdamons().get(0));

        //Verificação
        Assertions.assertTrue(jogador.getAdamons().size() < TesteUtils.obterAdamons().size());
    }

    @Test
    void deveConseguirRecuperarAdamon(){
        Jogador jogador = new Jogador();
        jogador.setAdamons(Collections.singletonList(TesteUtils.obterAdamon()));

        Inventario inventario = new Inventario();
        inventario.setAdamons(Collections.singletonList(new Adamon()));
        inventario.setJogador(jogador);

        InventarioService inventarioService = new InventarioService();
        inventarioService.recoverAdamon(inventario, inventario.getAdamons().get(0));

        Assertions.assertTrue(jogador.getAdamons().size() > 1);
    }
}

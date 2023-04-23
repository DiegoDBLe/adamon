package br.com.ada.americanas.adamon.service;

import br.com.ada.americanas.adamon.model.Adamon;
import br.com.ada.americanas.adamon.model.Inventario;
import br.com.ada.americanas.adamon.model.Jogador;

import java.util.ArrayList;
import java.util.List;

public class InventarioService {

    public void depositAdamon(Inventario inventario, Adamon adamon){
        Jogador jogador = inventario.getJogador();
        List<Adamon> adamons = jogador.getAdamons();

        boolean possuiAdamon = adamons.contains(adamon);
        boolean equipeMaiorQueUm = adamons.size() > 1;

        if(possuiAdamon && equipeMaiorQueUm){
            inventario.getAdamons().add(adamon);
            List<Adamon> adamonsJogador = new ArrayList<>(jogador.getAdamons());
            adamonsJogador.remove(adamon);
            jogador.setAdamons(adamonsJogador);
        }
    }

    public void recoverAdamon(Inventario inventario, Adamon adamon){
        Jogador jogador = inventario.getJogador();

        boolean temEspacoNaEquipe = jogador.getAdamons().size() < 6 ;

        if(temEspacoNaEquipe){
            List<Adamon> adamonsInventario = new ArrayList<>(inventario.getAdamons());
            adamonsInventario.remove(adamon);

            List<Adamon> equipeJogador = new ArrayList<>(jogador.getAdamons());
            equipeJogador.add(adamon);
            jogador.setAdamons(equipeJogador);
        }
    }
}

package br.com.ada.americanas.adamon.controller;

import br.com.ada.americanas.adamon.model.Adamon;
import br.com.ada.americanas.adamon.model.Jogador;
import br.com.ada.americanas.adamon.service.AdamonService;
import br.com.ada.americanas.adamon.service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/jogador")
@RestController
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @Autowired
    private AdamonService adamonService;

    @PutMapping("/comprar-adamon")
    public void buyAdamon(@RequestParam("idAdamon") Long idAdamon, @RequestParam("idJogador")Long idJogador) {
        Jogador jogador = jogadorService.finById(idJogador);
        Adamon adamon = adamonService.finById(idAdamon);
        jogadorService.buyAdamon(jogador, adamon);
    }

    @PostMapping
    public Jogador createNewJogador(Jogador jogador){
        return jogadorService.createNewJogador(jogador);
    }

}

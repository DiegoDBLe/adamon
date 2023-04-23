package br.com.ada.americanas.adamon.service;

import br.com.ada.americanas.adamon.model.Adamon;
import br.com.ada.americanas.adamon.model.Jogador;
import br.com.ada.americanas.adamon.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class JogadorService {
    @Autowired
    private JogadorRepository jogadorRepository;


    /* 01 - Exercicio:
        1 - Implementar preços na classe 'Adamon'
        2 - Implementar método de compra do 'Adamon'
        3 - Um jogador só pode ter no máximo 6 adamons em sua equipe
        4 - Escrever testes para este método
        5 - Pesquisar como testar um método void com 'Mockito'
     */

     /* 02 - Exercicio:
    Criar um método batalhar que recebe dois jogadores, e este método será responsável pela
    lógica de uma batalha entre duas equipes de Adamons. A lógica da batalha fica a sua escolha,
    um jogador será vitorioso o adversário não possuir mais adamons vivos (vida > 0);
     */

    public boolean temAdamonsVivos(List<Adamon> equipe){
        for (Adamon adamons : equipe) {
            if (adamons.getVida() > 0) {
                return true;
            }
        }
        return false;
    }

    public Adamon escolherAdamon(List<Adamon> equipe) {
        Adamon adamonEscolhido = null;
        for (Adamon adamon: equipe ) {
            if (adamon.getVida() > 0) {
                if(adamonEscolhido == null || adamon.getVida() < adamonEscolhido.getVida()) {
                    adamonEscolhido = adamon;
                }
            }
        }
        return adamonEscolhido;
    }

    public void batalhar(Jogador jogador, Jogador adversario){
        List<Adamon> equipejogador = jogador.getAdamons();
        List<Adamon> equipeAdversario = adversario.getAdamons();

        while(this.temAdamonsVivos(equipejogador) && this.temAdamonsVivos(equipeAdversario)){
            Adamon adamonJogador1 = escolherAdamon(equipejogador);
            Adamon adamonJogador2 = escolherAdamon(equipeAdversario);

            adamonJogador1.atacar(adamonJogador2);
            adamonJogador2.atacar(adamonJogador1);
        }

        if (this.temAdamonsVivos(equipejogador)){
            System.out.println(jogador.getNickname() + "Venceu a Batalha!");
        }else {
            System.out.println(adversario.getNickname() + "Venceu a Batalha!");
        }
    }


    public void buyAdamon(Jogador jogador, Adamon adamon) {
        List<Adamon> equipeAdamonJogador = jogador.getAdamons();
        BigDecimal saldoAtual = jogador.getSaldo();
        BigDecimal precoAdamon = adamon.getPreco();

        boolean possuiSaldoSuficiente = saldoAtual.compareTo(precoAdamon) > 0;
        boolean possuiEspacoNaEquipe = equipeAdamonJogador.size() < 6;

        if (possuiEspacoNaEquipe && possuiSaldoSuficiente) {
            equipeAdamonJogador.add(adamon);
            jogador.setSaldo(saldoAtual.subtract(precoAdamon));
            updateJogador(jogador, jogador.getId());
        } else if (!possuiSaldoSuficiente) {
            throw new RuntimeException("Não possui saldo suficiente");
        } else if (!possuiEspacoNaEquipe) {
            throw new RuntimeException("Não possui espaço na equipe");
        }
    }


    public Jogador finById(Long id){
        return jogadorRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Não encontramos o jogador com ID: " + id));
    }

    public Jogador createNewJogador(Jogador jogador){
        return jogadorRepository.save(jogador);
    }

    public void updateJogador(Jogador jogador, Long idJogador){
        finById(idJogador);
        jogador.setId(idJogador);
        jogadorRepository.save(jogador);
    }
}

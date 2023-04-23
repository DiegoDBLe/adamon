package br.com.ada.americanas.adamon.dto;

import br.com.ada.americanas.adamon.model.Jogador;

public class JogadorDtoResponse {

    public static Jogador converterDto(JogadorDTO dto){
        Jogador jogador = new Jogador();
        jogador.setNickname(dto.getNickName());
        jogador.setPassword(dto.getPassword());
        return jogador;
    }
}
